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

package org.eevolution.context.dictionary.infrastructure.domain.repository

import io.getquill.NamingStrategy
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import org.eevolution.context.dictionary.domain.entityTypeRepositoryApi.EntityTypeRepository
import org.eevolution.context.dictionary.domain.model.EntityType
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{Id, TableDirect}
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try

trait entityTypeRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
      val context: JdbcContext[Dialect, Naming],
      val blocking: Blocking.Service
  ) extends EntityTypeRepository.Service {

    import context._

    override def getById(
        entityTypeId: Id
    ): RIO[EntityTypeRepository, Option[EntityType]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[EntityType]
                .filter(_.entityTypeId == lift(entityTypeId))
            ).map(_.headOption)
          )
        )
      )

    override def getByEntityType(
        entityType: String
    ): RIO[EntityTypeRepository, Option[EntityType]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[EntityType]
                .filter(_.entityType == lift(entityType))
            ).map(_.headOption)
          )
        )
      )

    override def getByName(
        name: String
    ): RIO[EntityTypeRepository, Option[EntityType]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[EntityType]
                .filter(_.name == lift(name))
            ).map(_.headOption)
          )
        )
      )

    override def getAll: RIO[EntityTypeRepository, List[EntityType]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[EntityType])
          )
        )
      )

    override def getAllByClientId(
        tenantId: TableDirect
    ): RIO[EntityTypeRepository, List[EntityType]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[EntityType].filter(_.tenantId == lift(tenantId)))
          )
        )
      )

    override def save(entityType: EntityType): RIO[Any, EntityType] =
      RIO.fromTry(
        Try(EntityWrapper.save(entityType, entityTypeProperties))
      )

    implicit val entityTypeSchemaMeta: context.SchemaMeta[EntityType] =
      schemaMeta[EntityType](
        "AD_EntityType",
        _.entityTypeId -> "AD_EntityType_ID",
        _.tenantId -> "AD_Client_ID",
        _.organizationId -> "AD_Org_ID",
        _.entityType -> "EntityType",
        _.isActive -> "IsActive",
        _.created -> "Created",
        _.createdBy -> "CreatedBy",
        _.updated -> "Updated",
        _.updatedBy -> "UpdatedBy",
        _.name -> "Name",
        _.description -> "Description",
        _.help -> "Help",
        _.version -> "Version",
        _.modelPackage -> "ModelPackage",
        _.classPath -> "ClassPath",
        _.processing -> "Processing",
        _.uuid -> "UUID"
      )

    def entityTypeProperties: Seq[(String, String)] =
      entityTypeSchemaMeta.entity.ast.quat.renames.toSeq
  }

}
