package org.musical.ticketing.service;

import java.util.List;
import java.util.Optional;
import org.musical.ticketing.domain.Musical;
import org.musical.ticketing.repositories.MusicalsRepository;

public class MusicalsService {
  private final MusicalsRepository musicalsRepository;

  public MusicalsService() {
    this.musicalsRepository = new MusicalsRepository();
  }

  public Optional<Musical> getMusicalById(Long id) {
    return musicalsRepository.findById(id, Musical.empty());
  }

  public List<Musical> getAllMusicals() {
    return musicalsRepository.findAll(Musical.empty());
  }

  public List<Musical> searchMusicals(String query) {
      if(query.isEmpty()) {
          return getAllMusicals();
      }
    return musicalsRepository.search(query);
  }
}
