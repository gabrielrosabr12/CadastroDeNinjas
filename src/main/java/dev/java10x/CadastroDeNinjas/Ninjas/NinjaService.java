package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.infra.exceptions.NinjaNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }


    // Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        //
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());

    }

    // Listar todos os meus ninjas por ID
    // orElse é para caso não existir o ninja com aquele id, vai retornar nulo
    public NinjaDTO listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
        return ninjaModel.map(ninjaMapper::map).orElseThrow(() -> new NinjaNotFoundExceptions("Ninja não encontrado com o ID ("+id+")"));
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        //mapeio de acordo com as informações que o usuario passou e retorno um NinjaModel
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        //Salvo no banco de dados esse ninjaModel
        ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    // Deletar um ninja por id
    public void deletarNinjaId(Long id){
        ninjaRepository.deleteById(id);
    }


    // Atualizar um ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaAtualizado){
        NinjaModel ninjaExistente = ninjaRepository.findById(id)
                .orElseThrow(() -> new NinjaNotFoundExceptions("O ninja do ID ("+id+") não consta no banco de dados!"));

        if (ninjaAtualizado.getNome() != null){
            ninjaExistente.setNome(ninjaAtualizado.getNome());
        }
        if (ninjaAtualizado.getEmail() != null){
            ninjaExistente.setEmail(ninjaAtualizado.getEmail());
        }
        if (ninjaAtualizado.getIdade() > 0) {
            ninjaExistente.setIdade(ninjaAtualizado.getIdade());
        }
        if (ninjaAtualizado.getMissoes() != null){
            ninjaExistente.setMissoes(ninjaAtualizado.getMissoes());
        }
        if (ninjaAtualizado.getRank() != null){
            ninjaExistente.setRank(ninjaAtualizado.getRank());
        }
        if (ninjaAtualizado.getImgUrl() != null){
            ninjaExistente.setImgUrl(ninjaAtualizado.getImgUrl());
        }

        NinjaModel ninjaSalvo = ninjaRepository.save(ninjaExistente);

        return ninjaMapper.map(ninjaSalvo);

    }

}


