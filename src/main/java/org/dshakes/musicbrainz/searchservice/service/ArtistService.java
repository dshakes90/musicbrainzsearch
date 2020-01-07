package org.dshakes.musicbrainz.searchservice.service;

import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.dshakes.musicbrainz.searchservice.domain.Artist;
import org.dshakes.musicbrainz.searchservice.repository.ArtistRepository;
import org.dshakes.musicbrainz.searchservice.repository.search.ArtistSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Artist}.
 */
@Service
@Transactional
public class ArtistService {

    private final Logger log = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;

    private final ArtistSearchRepository artistSearchRepository;

    public ArtistService(ArtistRepository artistRepository, ArtistSearchRepository artistSearchRepository) {
        this.artistRepository = artistRepository;
        this.artistSearchRepository = artistSearchRepository;
    }

    /**
     * Save a artist.
     *
     * @param artist the entity to save.
     * @return the persisted entity.
     */
    public Artist save(Artist artist) {
        log.debug("Request to save Artist : {}", artist);
        Artist result = artistRepository.save(artist);
        artistSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the artists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Artist> findAll(Pageable pageable) {
        log.debug("Request to get all Artists");
        return artistRepository.findAll(pageable);
    }


    /**
     * Get one artist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Artist> findOne(Long id) {
        log.debug("Request to get Artist : {}", id);
        return artistRepository.findById(id);
    }

    /**
     * Delete the artist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Artist : {}", id);
        artistRepository.deleteById(id);
        artistSearchRepository.deleteById(id);
    }

    /**
     * Search for the artist corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Artist> searchByName(String query) {
        log.debug("Request to search for a page of Artists for query {}", query);
        query = MultiFieldQueryParser.escape(query);
        return artistSearchRepository.search(queryStringQuery("name:" + query), new PageRequest(0, 5));    }

    @Transactional(readOnly = true)
    public Page<Artist> searchByID(String query) {
        log.debug("Request to search for a page of Artists for query {}", query);
        query = MultiFieldQueryParser.escape(query);
        return artistSearchRepository.search(queryStringQuery("id:" + query), new PageRequest(0, 5));
    }

    @Transactional(readOnly = true)
    public long count() {
        return artistSearchRepository.count();
    }
}
