package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.infra.exceptions.MissaoNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();

        return missoes.stream().map(missoesMapper::map).collect(Collectors.toList());
    }

    public MissoesDTO listarMissoesId(Long id){
        Optional<MissoesModel> missao = missoesRepository.findById(id);
        return missao.map(missoesMapper::map).orElseThrow(() -> new MissaoNotFoundExceptions("Missao do id ("+id+") não encontrada!"));
    }


    public void deletarMissoes(Long id){
        missoesRepository.deleteById(id);
    }

    public void atualizarMissoes(Long id,MissoesDTO missaoAtualizada){
        MissoesDTO missoesDTO = listarMissoesId(id);

        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel.setId(missaoAtualizada.getId());
        missoesModel.setNome(missaoAtualizada.getNome());
        missoesModel.setDificuldade(missaoAtualizada.getDificuldade());
        missoesRepository.save(missoesModel);
    }

    public MissoesDTO criarMissao(MissoesDTO missao){
        MissoesModel missoesModel = missoesMapper.map(missao);
        missoesRepository.save(missoesModel);
        return missoesMapper.map(missoesModel);
    }

}
