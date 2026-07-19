package ordemServico.domain.enums;

public enum Priridade {
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

	private Integer codigo;
	private String descricao;

	private Priridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Priridade toPerfil(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Priridade x: Priridade.values()) {
			if(cod.equals(x.codigo)) {
				return x;
			}
		}
		
		throw new  IllegalArgumentException("Perfil Invalido");
	}

}
