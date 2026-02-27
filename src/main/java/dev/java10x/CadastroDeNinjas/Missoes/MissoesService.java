package dev.java10x.CadastroDeNinjas.Missoes;

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
        return missao.map(missoesMapper::map).orElse(null);
    }


    public void deletarMissoes(Long id){
        missoesRepository.deleteById(id);
    }

    public void atualizarMissoes(Long id,MissoesModel missaoAtualizada){
        MissoesDTO missoesDTO = listarMissoesId(id);

        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel.setId(missaoAtualizada.getId());
        missoesModel.setNome(missaoAtualizada.getNome());
        missoesModel.setDificuldade(missaoAtualizada.getDificuldade());
        missoesRepository.save(missoesModel);
    }
}
