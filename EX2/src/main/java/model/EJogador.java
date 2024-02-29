package model;

public class EJogador {

	private String time;
	private String nome;
	private char sexo;
	private int id;

	
	public EJogador() {
		this.nome = "";
		this.id = -1;
		this.time = "";
		this.sexo = '*';
	}
	
	public EJogador(int id, String nome, String time, char sexo) {
		this.id = id;
		this.nome = nome;
		this.time = time;
		this.sexo = sexo;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public String gettime() {
		return time;
	}

	public void settime(String time) {
		this.time = time;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", time=" + time + ", sexo=" + sexo + "]";
	}

	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
	}	
}
