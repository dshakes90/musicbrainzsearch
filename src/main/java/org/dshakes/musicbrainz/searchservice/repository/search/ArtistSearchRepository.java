package org.dshakes.musicbrainz.searchservice.repository.search;

import org.dshakes.musicbrainz.searchservice.domain.Artist;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Artist} entity.
 */
public interface ArtistSearchRepository extends ElasticsearchRepository<Artist, Long> {
}
