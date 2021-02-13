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
import org.eevolution.context.dictionary.domain.entityRepositoryApi.EntityRepository
import org.eevolution.context.dictionary.domain.model.{Entity, EntityType}
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{Id, TableDirect}
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try

trait entityRepositoryBase {
  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
      val context: JdbcContext[Dialect, Naming],
      val blocking: Blocking.Service
  ) extends EntityRepository.Service {

    import context._

    override def getById(
        entityId: Id
    ): RIO[EntityRepository, Option[Entity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[Entity]
                .filter(_.entityId == lift(entityId))
            ).map(_.headOption)
          )
        )
      )

    def getByEntityType(
        entityType: EntityType
    ): RIO[EntityRepository, List[Entity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[Entity]
                .filter(_.entityType == lift(entityType.entityType))
            )
          )
        )
      )

    override def getAll: RIO[EntityRepository, List[Entity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Entity])
          )
        )
      )

    override def getAllByClientId(
        tenantId: TableDirect
    ): RIO[EntityRepository, List[Entity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Entity].filter(_.tenantId == lift(tenantId)))
          )
        )
      )

    override def save(entity: Entity): RIO[Any, Entity] =
      RIO.fromTry(
        Try(EntityWrapper.save(entity, entityProperties))
      )

    implicit val entitySchemaMeta: context.SchemaMeta[Entity] =
      schemaMeta[Entity](
        "AD_Table",
        _.entityId -> "AD_Table_ID",
        _.tenantId -> "AD_Client_ID",
        _.organizationId -> "AD_Org_ID",
        _.isActive -> "IsActive",
        _.created -> "Created",
        _.createdBy -> "CreatedBy",
        _.updated -> "Updated",
        _.updatedBy -> "UpdatedBy",
        _.name -> "Name",
        _.description -> "Description",
        _.help -> "Help",
        _.tableName -> "TableName",
        _.isView -> "IsView",
        _.accessLevel -> "AccessLevel",
        _.entityType -> "EntityType",
        _.windowId -> "AD_Window_ID",
        _.windowPOId -> "PO_Window_ID",
        _.valueRuleId -> "AD_Val_Rule_ID",
        _.loadSeq -> "LoadSeq",
        _.isSecurityEnabled -> "IsSecurityEnabled",
        _.isDeleteable -> "IsDeleteable",
        _.isHighVolume -> "IsHighVolume",
        _.isImportTable -> "ImportTable",
        _.isChangeLog -> "IsChangeLog",
        _.replicationType -> "ReplicationType",
        _.copyColumnsFromTable -> "CopyColumnsFromTable",
        _.isCentrallyMaintained -> "IsCentrallyMaintained",
        _.acTriggerLength -> "AcTriggerLength",
        _.isDocument -> "IsDocument",
        _.isIgnoreMigration -> "IsIgnoreMigration",
        _.uuid -> "UUID"
      )

    def entityProperties: Seq[(String, String)] =
      entitySchemaMeta.entity.ast.quat.renames.toSeq
  }
}
