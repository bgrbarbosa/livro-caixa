package br.com.bgrbarbosa.livro_caixa_api.model.mapper;

import br.com.bgrbarbosa.livro_caixa_api.model.Usuario;
import br.com.bgrbarbosa.livro_caixa_api.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario usuario);

    List<UsuarioDTO> toDtoList(List<Usuario> usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<Usuario> toEntityList(List<UsuarioDTO> usuarioDTO);

}
