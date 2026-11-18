package negocio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Aluno {
    private int id;
    private String nome; 

}
