package com.egem.ram.serviceapi;

import com.egem.ram.dto.EpisodeDto;
import com.egem.ram.dto.EpisodeResponse;
import com.egem.ram.dto.PageDto;
import org.springframework.data.domain.Page;

public interface EpisodeService {

  EpisodeResponse read(Long episodeId);

  void create(EpisodeDto episode);

  Page<EpisodeResponse> list(PageDto episodePageDto);
}
