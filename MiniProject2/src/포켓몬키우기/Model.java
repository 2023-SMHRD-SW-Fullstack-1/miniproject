package 포켓몬키우기;

public class Model {
	//VO(value object 
	
	//필드

	private String musicPath;
	
	//생성자
	public Model( String musicPath) {
		this.musicPath = musicPath;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	
	
}
