package com.app.projectjar.service.suggest;

import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("suggest") @Primary
public class SuggestServiceImpl implements SuggestService {
    private final SuggestRepository suggestRepository;
    private final MemberRepository memberRepository;


    @Override
    public void register(SuggestDTO suggestDTO) {
        suggestRepository.save(toSuggestEntity(suggestDTO));
    }

    @Override
    public List<SuggestDTO> getSuggestList(Pageable pageable) {
        List<SuggestDTO> suggestDTOS = new ArrayList<>();
        suggestRepository.findAllWithPaging_QueryDsl(pageable).forEach(
                suggest -> {
                    suggestDTOS.add(toSuggestDTO(suggest));
                }
        );
        return suggestDTOS;
    }

    @Override
    public SuggestDTO getSuggest(Long suggestId) {
        Optional<Suggest> suggest = suggestRepository.findByIdSuggest_QueryDsl(suggestId);
        return toSuggestDTO(suggest.get());
    }
}
