package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.repository.inquire.InquireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("inquire")
@Primary
@Slf4j
public class InquireServicelmpl implements InquireService {
    private final InquireRepository inquireRepository;

    public InquireServicelmpl(InquireRepository inquireRepository) {
        this.inquireRepository = inquireRepository;
    }

    @Override
    public Page<InquireDTO> getAllInquiresWithPaging(int page) {
        Page<Inquire> inquires = inquireRepository.findAllWithPaging_QueryDSL(PageRequest.of(page, 10));
        List<InquireDTO> inquireDTOS = inquires.getContent().stream()
                .map(this::toInquireDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(inquireDTOS, inquires.getPageable(), inquires.getTotalElements());
    }


    @Override
    public void register(InquireDTO inquireDTO) {
        inquireRepository.save(toInquireEntity(inquireDTO));
    }

    @Override
    public InquireDTO getInquire(Long inquireId) {
        Optional<Inquire> inquire = inquireRepository.findById(inquireId);
        return toInquireDTO(inquire.get());
    }

    @Override
    public void deleteInquire(Long inquireId) { inquireRepository.deleteById(inquireId); }

    @Override
    public void updateInquire(Long inquireId, InquireDTO inquireDTO) {
        Optional<Inquire> optionalInquire = inquireRepository.findById(inquireId);
        if (optionalInquire.isPresent()) {
            Inquire existingInquire = optionalInquire.get();
            existingInquire.setInquireTitle(inquireDTO.getInquireTitle());
            existingInquire.setInquireContent(inquireDTO.getInquireContent());
            inquireRepository.save(existingInquire);
        } else {
        }
    }

}

