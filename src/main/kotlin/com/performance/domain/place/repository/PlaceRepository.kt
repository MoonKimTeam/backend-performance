package com.performance.domain.place.repository

import com.performance.domain.place.model.Place
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<Place, Long>
