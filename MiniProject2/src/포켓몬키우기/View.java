package 포켓몬키우기;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.Arrays;

public class View {

	public static void main(String[] args) {
		DAPokeMonDAO dao = new DAPokeMonDAO();
		Scanner sc = new Scanner(System.in);
		ArrayList<PKDTO> data = new ArrayList<>();
		art art = new art();
		boolean teck = false;
		while (true) {
			int count = 0;
			int day = 0;
			int prevSw = 0;
			if (teck) {
				teck = false;
				PKDTO firstPkDto = data.get(0);
				Controller cont = new Controller("op");
				cont.play();
				int sw = 0;
				while (true) {
					// 아스키 아트 and 소리 합쳐놓는게 편함. 앞에는 출력 뒤는 소리출력 + 이렇게 호출하게.
					System.out.println("");
					System.out.println("");
					System.out.println(firstPkDto.toString());
					System.out.println("");
					System.out.println("");
					System.out.println("1.밥먹기 2.잠자기 3.씻기 4.모험하기 5.종료하기");
					System.out.println("");
					System.out.println("");
					System.out.println((day + 1) + "일 " + (count + 1) + " 번째 행동");
					System.out.println("");
					System.out.println("");
					try {
						sw = sc.nextInt();
					} catch (Exception e) {
						sw = 0;
					}

					if (sw == prevSw) { // 이전 선택지와 같은 선택지를 선택한 경우
						System.out.println("이미 선택한 선택지입니다. 다른 선택지를 선택해주세요.");
						continue; // 다시 반복문의 처음으로 돌아가서 선택지를 다시 받음
					} else {
						prevSw = sw; // 이전 선택지를 현재 선택지로 업데이트
					}

					if (sw == 1) {
						if (firstPkDto.getPKNAME().equals("피카츄")) {
							art.eatP();
						}
						if (firstPkDto.getPKNAME().equals("파이리"))
							art.eatC();
						if (firstPkDto.getPKNAME().equals("꼬북이"))
							art.eatS();
						Controller conts = new Controller(firstPkDto.getPKNAME());
						conts.next(0);
						System.out.println("");
						System.out.println("밥을 우걱 우걱 먹습니다");
						System.out.println("");
						firstPkDto = data.get(0);
						int currentStt = firstPkDto.getSTT();
						int newStt = currentStt + 40;

						// STT 값이 100을 넘지 않도록 제한하기
						if (newStt > 100) {
							newStt = 100;
						}

						// PKDTO 객체의 STT 값을 업데이트하기
						firstPkDto.setSTT(newStt);
						System.out.println("밥을 먹여 포만감이 " + newStt + "가 되었습니다.");
					} else if (sw == 2) {
						if (firstPkDto.getPKNAME().equals("피카츄"))
							art.sleepP();
						if (firstPkDto.getPKNAME().equals("파이리"))
							art.sleepC();
						if (firstPkDto.getPKNAME().equals("꼬북이"))
							art.sleepS();
						Controller conts = new Controller(firstPkDto.getPKNAME());
						conts.next(1);
						System.out.println("잠자기");
						firstPkDto = data.get(0);
						int currFtg = firstPkDto.getFTG(); // 현재 FTG 값 가져오기
						currFtg += 30; // FTG 30 회복
						if (currFtg > 100) { // 최대치를 넘어가면 최대치로 맞춰주기
							currFtg = 100;
						}
						firstPkDto.setFTG(currFtg); // 변경된 FTG 값 저장
						System.out.println("피로도가 " + currFtg + "로 회복되었습니다.");
						int currHP = firstPkDto.getHP(); // 현재 HP 값 가져오기
						if (firstPkDto.getHP() < 24 + (firstPkDto.getLV() * 3))
							currHP = (firstPkDto.getLV()) + 24; // HP 회복
						firstPkDto.setHP(currHP); // 변경된 HP 값 저장
						// 잠자기로 인해 포만감이 떨어짐
						int currentStt = firstPkDto.getSTT();
						int newStt = currentStt - 10;

						if (newStt < 0) {
							newStt = 0;
						}
						firstPkDto.setSTT(newStt);

						System.out.println("HP가 " + currHP + "로 회복되었습니다.");
						System.out.println("잠자서 포만감이 " + newStt + "가 되었습니다.");
					} else if (sw == 3) {
						if (firstPkDto.getPKNAME().equals("피카츄"))
							art.washP();
						if (firstPkDto.getPKNAME().equals("파이리"))
							art.washC();
						if (firstPkDto.getPKNAME().equals("꼬북이"))
							art.washS();
						Controller conts = new Controller(firstPkDto.getPKNAME());
						conts.next(2);
						System.out.println("씻기");
						firstPkDto = data.get(0);
						if (firstPkDto.getCNDTN() < 5) { // CNDTN이 100이하일 때만 증가하도록 함
							firstPkDto.setCNDTN(firstPkDto.getCNDTN() + 1);
							System.out.println("컨디션이 1 증가했습니다. 현재 CNDTN: " + firstPkDto.getCNDTN());
						} else {
							System.out.println("컨디션이 이미 최대치입니다.");
						}
					} else if (sw == 4) {
						System.out.println("모험을 떠납니다");

						while (firstPkDto.getFTG() > 0 && firstPkDto.getHP() > 0) {
							int enemyHP = 0, enemyLV = 0, enemyATK = 0, enemyDEF = 0;
							String enemyName = "";
							if (firstPkDto.getLV() <= 10) {
								System.out.println("로켓단의 꼬마돌이 나타났다!");
								enemyName = "꼬마돌";
								enemyHP = (int) 200 + (day / 3);
								enemyLV = (int) 8 + (day / 3);
								enemyATK = (int) 13 + (day / 3);
								enemyDEF = (int) 4 + (day / 3);
							} else if (firstPkDto.getLV() <= 20) {
								System.out.println("로켓단의 피존이 나타났다!");
								enemyName = "피존";
								enemyHP = 100;
								enemyLV = 50;
								enemyATK = 40;
								enemyDEF = 30;
							} else {
								// ...
							}

							int myATK = firstPkDto.getATK();

							switch (firstPkDto.getCNDTN()) {
							case 1:
								myATK = (int) (myATK * 1 - enemyDEF);
								break;
							case 2:
								myATK = (int) (myATK * 2 - enemyDEF);
								break;
							case 3:
								myATK = (int) (myATK * 3 - enemyDEF);
								break;
							case 4:
								myATK = (int) (myATK * 4 - enemyDEF);
								break;
							default:
								myATK = (int) (myATK * 5 - enemyDEF);
								break;
							}

							while (true) {
								System.out.println("====================");
								System.out.printf("%s 레벨 %d HP %d 공격력 %d 방어력 %d\n", enemyName, enemyLV, enemyHP,
										enemyATK, enemyDEF);
								System.out.printf("%s 레벨 %d HP %d 공격력 %d 방어력 %d\n", firstPkDto.getPKNAME(),
										firstPkDto.getLV(), firstPkDto.getHP(), firstPkDto.getATK(),
										firstPkDto.getDEF());
								System.out.println("====================");
								System.out.print("1.싸운다 2.도망친다 ");
								int menu = sc.nextInt();
								
								Controller conts = new Controller("bt");
								conts.next(1);
								if (menu == 1) {
									enemyHP -= myATK;
									System.out.println("공격 효과가 굉장했다!");
									if (enemyHP <= 0) {
										System.out.println("전투 종료");
										break;
									} else {
										int damage = enemyATK - firstPkDto.getDEF();
										if (damage < 0)
											damage = 0;
										firstPkDto.setHP(firstPkDto.getHP() - damage);
										System.out.printf("%s이(가) %d의 데미지를 입었습니다.\n", firstPkDto.getPKNAME(), damage);
										if (firstPkDto.getHP() <= 0) {
											System.out.println("포켓몬이 지쳐 쓰러졌습니다.");
											if (firstPkDto.getHP() <= 0) {
												firstPkDto.setHP(0);
											}
											break;
										}
									}
								} else {
									break;
								}
							}

							firstPkDto.setXP(firstPkDto.getXP() + 5);
							firstPkDto.setSTT(firstPkDto.getSTT() - 40);
							firstPkDto.setFTG(firstPkDto.getFTG() - 10);
							if (enemyHP <= 0 || firstPkDto.getFTG() <= 0) {
								if (enemyHP <= 0) {
									System.out.println("승리했습니다!");
								} else {
									System.out.println("포켓몬이 지쳐 쓰러졌습니다.");
								}
								break;
							}
							break;
						}

					} else if (sw == 5) {
						System.out.println("종료합니다");
						dao.update(firstPkDto);
						break;
					} else
						System.out.println("다른키 입력 바랍니다");

					count++;
					if (count % 3 == 0) {
						firstPkDto.setSTT(firstPkDto.getSTT() - 20); // STT 값 40 감소
						firstPkDto.setFTG(firstPkDto.getFTG() - 10); // FTG 값 20 감소
						firstPkDto.setCNDTN(firstPkDto.getCNDTN() - 1); // CNDTN 값 1 감소
						System.out.println("");
						System.out.println("하루가 지났습니다. 포만감 -40, 피로도 -20, 컨디션 -1");
						System.out.println("");
						day++;
						count = 0;
					}
					firstPkDto = data.get(0);
					if (firstPkDto.getSTT() <= 0 || firstPkDto.getFTG() <= 0) {
						if (firstPkDto.getSTT() <= 0) {
							System.out.println("");
							System.out.println("배고파서 게임오버");
						} else
							System.out.println("피곤해서 게임오버");
						dao.update(firstPkDto);
						break;
					}
					if (firstPkDto.getXP() >= 10) {
						System.out.println("레벨이 올라갔습니다 !!");
						firstPkDto.setXP(firstPkDto.getXP() - 10);
						firstPkDto.setLV(firstPkDto.getLV() + 1);
						firstPkDto.setATK(firstPkDto.getATK() + 1);
						firstPkDto.setDEF(firstPkDto.getDEF() + 1);
						System.out.printf("%d 레벨 %d 공격력 %d 방어력 \n", firstPkDto.getLV(), firstPkDto.getATK(),
								firstPkDto.getDEF());
					}
				}
			}

			System.out.print("(1) 회원가입 (2) 로그인 (3) 종료");
			int lo = sc.nextInt();
			if (lo == 1) {
				System.out.println("===회원가입 시도===");
				System.out.print("id 입력 >> ");
				String id = sc.next();
				System.out.print("pw 입력 >> ");
				String pw = sc.next();
				int ck = dao.join(id, pw);
			} else if (lo == 2) {
				System.out.print("id 입력 >> ");
				String id = sc.next();
				System.out.print("pw 입력 >> ");
				String pw = sc.next();
				int ck = dao.login(id, pw);
				if (ck >= 0) {
					teck = true;
					if (ck == 0) {
						System.out.println("성공");
						System.out.println("포켓몬을 골라주세요!");
						System.out.println("1. 피카츄, 2.파이리, 3.꼬북이");
						int inPs = sc.nextInt();
						String name;
						if (inPs == 1) {
							name = "피카츄";
							PKDTO temp = new PKDTO(1, 10, 10, 1, 25, 0, 100, 100, 3, id, pw, name);
							data.add(temp);
						} else if (inPs == 2) {
							name = "파이리";
							PKDTO temp = new PKDTO(1, 10, 10, 1, 25, 0, 100, 100, 3, id, pw, name);
							data.add(temp);
						} else if (inPs == 3) {
							name = "꼬북이";
							PKDTO temp = new PKDTO(1, 10, 10, 1, 25, 0, 100, 100, 3, id, pw, name);
							data.add(temp);
						}
					} else {
						System.out.println("저장된 데이터를 불러옵니다");
						PKDTO temp = dao.select(id, pw);
						data.add(temp);
					}

				} else
					System.out.println("로그인 실패");
				/////////////////////////////////////////////////

			} else if (lo == 3) {
				System.out.println("종료합니다");
				break;
			} else
				System.out.println("재입력 바랍니다");

		}
	}
}
