package com.egem.ram.controller;

import com.egem.ram.dto.EpisodeDto;
import com.egem.ram.dto.EpisodeResponse;
import com.egem.ram.dto.PageDto;
import com.egem.ram.dto.SuccessResponse;
import com.egem.ram.serviceapi.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Episode objesi üzerinde işlemlerin yapıldığı controller katmanıdır.
 */
@RestController
@RequestMapping(value = "rest/episode")
public class EpisodeController {

  private EpisodeService episodeService;

  /**
   * Episode id bilgisi ile bir episode objesi getiren methoddur.
   * @param episodeId
   * @return
   */
  @GetMapping("/{episodeId}")
  public ResponseEntity<EpisodeResponse> read(@PathVariable Long episodeId) {
    return new ResponseEntity<>(episodeService.read(episodeId), HttpStatus.OK);
  }

  /**
   * Bir episode objesi oluşturan methoddur.
   * @param episode
   * @return
   */
  @PostMapping("/create")
  public ResponseEntity<SuccessResponse> create(@RequestBody EpisodeDto episode) {
    episodeService.create(episode);
    return new ResponseEntity<>(
        new SuccessResponse("Episode oluşturma", "Episode başarılı bir şekilde oluşturuldu."),
        HttpStatus.OK);
  }

  /**
   * Episode bilgilerini sayfalı bir şekilde dönen methoddur.
   * @param episodePageDto
   * @return
   */
  @PostMapping("/list")
  public ResponseEntity<Page<EpisodeResponse>> list(@RequestBody PageDto episodePageDto) {
    return new ResponseEntity<>(episodeService.list(episodePageDto), HttpStatus.OK);
  }

  @Autowired
  public void setEpisodeService(EpisodeService episodeService) {
    this.episodeService = episodeService;
  }
}
