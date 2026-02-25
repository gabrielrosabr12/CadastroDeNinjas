package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    //private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
        //this.missoesMapper = missoesMapper;
    }

    public List<MissoesModel> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();

        return missoes;
    }

    public MissoesModel listarMissoesId(Long id){
        Optional<MissoesModel> missao = missoesRepository.findById(id);
        return missao.orElse(null);
    }

}
