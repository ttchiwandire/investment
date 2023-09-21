package za.co.momentum.investment.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import za.co.momentum.investment.dto.InvestorDTO;
import za.co.momentum.investment.dto.ProductDTO;
import za.co.momentum.investment.model.Investor;
import za.co.momentum.investment.model.InvestorProduct;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InvestorMapper {

    InvestorDTO mapInvestorEntityToDto(Investor investor);

    List<InvestorDTO> mapInvestorEntityListToDto(List<Investor> investor);

    Investor mapInvestorDTOToEntity(InvestorDTO investor);

    @BeforeMapping
    default void mapExtraFields(InvestorProduct source, @MappingTarget ProductDTO destination) {
        destination.setId(source.getProduct().getId());
        destination.setName(source.getProduct().getName());
        destination.setProductType(source.getProduct().getProductType());
    }


    List<ProductDTO> mapInvestorProductEntityToDTO(List<InvestorProduct> product);


}
