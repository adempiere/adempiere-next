/**
  * Copyright (C) 2003-2021, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com.
  * Modified by emeris.hernandez@e-evolution.com , www.e-evolution.com on 19/10/17.
  */

package org.eevolution.context.dictionary.domain.service

import org.eevolution.context.dictionary.domain.entityTypeRepositoryApi.EntityTypeRepository
import org.eevolution.context.dictionary.domain.entityTypeServiceApi.EntityTypeService
import org.eevolution.context.dictionary.domain.model.EntityType
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{Id, TableDirect}
import zio.RIO

trait entityTypeServiceBase {
  abstract class Service(
      entityTypeRepository: EntityTypeRepository.Service
  ) extends EntityTypeService.Service {

    override def getById(
        id: Id
    ): RIO[EntityTypeRepository, Option[EntityType]] =
      entityTypeRepository.getById(id)

    def getByName(name: String): RIO[EntityTypeRepository, Option[EntityType]] =
      entityTypeRepository.getByName(name)

    def getByEntityType(
        entityType: String
    ): RIO[EntityTypeRepository with EntityTypeRepository, Option[EntityType]] =
      entityTypeRepository.getByEntityType(entityType)

    override def getAll: RIO[EntityTypeRepository, List[EntityType]] =
      entityTypeRepository.getAll

    override def getAllByClientId(
        tenantId: TableDirect
    ): RIO[EntityTypeRepository, List[EntityType]] =
      entityTypeRepository.getAllByClientId(tenantId)

    override def save(
        entity: EntityType
    ): RIO[EntityTypeRepository, EntityType] =
      entityTypeRepository.save(entity)

  }
}
