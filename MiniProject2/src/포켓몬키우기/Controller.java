package 포켓몬키우기;

import java.util.ArrayList;
import javazoom.jl.player.*;

public class Controller {
	//곡 5개를 저장하는 ArrayList 생성
	ArrayList<Model> musicList = new ArrayList<Model>(10);
	
	MP3Player mp3= new MP3Player();
	//노래를 재생 / 정지할 수 있는 기능을 가진 객체 생성player.jar 이용
	
	//musiclist 노래 순서 관리 인덱스
	
	int index = 0;
	//생성자
	public Controller(String a) {
		//모델객체 생성후 바로 add
		if(a.equals("피카츄")) {
			musicList.add(new Model("music/피카츄.mp3"));
			musicList.add(new Model("music/피카.mp3"));
			musicList.add(new Model("music/피카피카.mp3"));
		}else if(a.equals("파이리")) {
			musicList.add(new Model("music/파이리.mp3"));
			musicList.add(new Model("music/파이리2.mp3"));
			musicList.add(new Model("music/파이파이파이리.mp3"));
		}else if(a.equals("꼬북이")) {
			musicList.add(new Model("music/꼬북이.mp3"));
			musicList.add(new Model("music/꼬부욱.mp3"));
			musicList.add(new Model("music/꼬북꼬북.mp3"));
		}else if(a.equals("op")) {
			musicList.add(new Model("music/오프닝노래.mp3"));
		}else if(a.equals("bt")) {
			musicList.add(new Model("music/로켓단등장.mp3"));
		}
	}
	
	public void play() {
		//현재 재생중인 음악이 있는지
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		//index 위치의 음악 재생
		mp3.play(musicList.get(index).getMusicPath());
		
	}
	
	public void stop() {
		mp3.stop();
	}
	
	public void next(int num) {
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		
		if(index < musicList.size()-1) {
			index = num;
		}else index = 0;		
		
		mp3.play(musicList.get(index).getMusicPath());
		
	}
	
	public void pre() {
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		
		if(index != 0) {
			index--;
		}else index = musicList.size()-1;		
		
		mp3.play(musicList.get(index).getMusicPath());
		
	}

}
