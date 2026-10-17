package org.example.frotaveiculos.repository;

import org.example.frotaveiculos.model.TipoVeiculo;
import org.example.frotaveiculos.model.Veiculo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VeiculoRepository {

    public final List<Veiculo> VEICULOS = new ArrayList<>();
    private Long contador = 1L;

    public List<Veiculo> getTodos() {
        return VEICULOS;
    }

    public void adicionar(Veiculo veiculo) {
        veiculo.setId(contador++);
        VEICULOS.add(veiculo);
    }

    public Veiculo buscarPorId(Long id) {
        for (Veiculo v : VEICULOS){
            if (v.getId().equals(id)){
                return v;
            }
        }
        return null;
    }

    public List<Veiculo> listarMotos() {
        List<Veiculo> buscarMotos = new ArrayList<>();
        for (Veiculo v : VEICULOS){
            if (v.getTipoVeiculo()== TipoVeiculo.MOTO){
                buscarMotos.add(v);
            }
        }
        return buscarMotos;
    }

}
