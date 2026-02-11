package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

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
        return ninjaModel.map(ninjaMapper::map).orElse(null);
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
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);

        if (ninjaExistente.isPresent()){
            NinjaModel ninjaModel =  ninjaMapper.map(ninjaAtualizado);
            ninjaModel.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaModel);
            return ninjaMapper.map(ninjaSalvo);
        }

        return null;
    }

}


