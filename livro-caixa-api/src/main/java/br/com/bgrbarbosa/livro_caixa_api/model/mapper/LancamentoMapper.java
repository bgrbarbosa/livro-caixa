package br.com.bgrbarbosa.livro_caixa_api.model.mapper;

import br.com.bgrbarbosa.livro_caixa_api.model.Lancamentos;
import br.com.bgrbarbosa.livro_caixa_api.model.dto.LancamentoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {

    LancamentoDTO toDto(Lancamentos caixa);

    List<LancamentoDTO> toDtoList(List<Lancamentos> caixa);

    Lancamentos toEntity(LancamentoDTO caixaDTO);

    List<Lancamentos> toEntityList(List<LancamentoDTO> caixaDTO);

}
