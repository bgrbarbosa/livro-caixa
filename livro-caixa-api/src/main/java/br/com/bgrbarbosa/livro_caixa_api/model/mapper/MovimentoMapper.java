package br.com.bgrbarbosa.livro_caixa_api.model.mapper;

import br.com.bgrbarbosa.livro_caixa_api.model.Movimento;
import br.com.bgrbarbosa.livro_caixa_api.model.dto.MovimentoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimentoMapper {

    MovimentoDTO toDto(Movimento movimento);

    List<MovimentoDTO> toDtoList(List<Movimento> movimento);

    Movimento toEntity(MovimentoDTO movimentoDTO);

    List<Movimento> toEntityList(List<MovimentoDTO> movimentoDTO);

}
