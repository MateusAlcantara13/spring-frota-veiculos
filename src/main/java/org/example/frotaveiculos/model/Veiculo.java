package org.example.frotaveiculos.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Veiculo {
    private Long id;
    private StatusVeiculo statusVeiculo;

    @NotNull(message = "Selecione uma categoria")
    private TipoVeiculo tipoVeiculo;

    @NotBlank(message = "Informe a placa")
    @Size(min = 7, max = 8, message = "A placa do veículo deve conter de 7 a 8 caracteres")
    private String placa;

    @NotBlank(message = "Informe um modelo")
    private String modelo;

    @NotNull(message = "Informe os km's do veículo")
    @PositiveOrZero(message = "O valor deve ser maior que 0")
    private Integer kmAtual;

    public Veiculo () {
        this.statusVeiculo = StatusVeiculo.DISPONIVEL;
    }

    public void usar() {
        if (statusVeiculo==StatusVeiculo.DISPONIVEL){
            statusVeiculo=StatusVeiculo.EM_USO;
        }
    }

    public void devolver() {
        if (statusVeiculo==StatusVeiculo.EM_USO || statusVeiculo==StatusVeiculo.MANUTENCAO){
            statusVeiculo=StatusVeiculo.DISPONIVEL;
        }
    }

    public void enviarManutencao() {
        if (statusVeiculo==StatusVeiculo.DISPONIVEL){
            statusVeiculo=StatusVeiculo.MANUTENCAO;
        }
    }
}
