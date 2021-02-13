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
import org.eevolution.context.dictionary.domain.attributeRepositoryApi.AttributeRepository
import org.eevolution.context.dictionary.domain.model.{Attribute, Entity}
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{Id, TableDirect}
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try

trait attributeRepositoryBase {
  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
      val context: JdbcContext[Dialect, Naming],
      val blocking: Blocking.Service
  ) extends AttributeRepository.Service {

    import context._

    override def getById(
        attributeId: Id
    ): RIO[AttributeRepository, Option[Attribute]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[Attribute]
                .filter(_.attributeId == lift(attributeId))
            ).map(_.headOption)
          )
        )
      )

    override def getAll: RIO[AttributeRepository, List[Attribute]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Attribute])
          )
        )
      )

    override def getByEntity(
        entity: Entity
    ): RIO[AttributeRepository, List[Attribute]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Attribute].filter(_.entityId == lift(entity.entityId)))
          )
        )
      )

    override def getAllByClientId(
        tenantId: TableDirect
    ): RIO[AttributeRepository, List[Attribute]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Attribute].filter(_.tenantId == lift(tenantId)))
          )
        )
      )

    override def save(attribute: Attribute): RIO[Any, Attribute] =
      RIO.fromTry(
        Try(EntityWrapper.save(attribute, attributeProperties))
      )

    implicit val attributeSchemaMeta: context.SchemaMeta[Attribute] =
      schemaMeta[Attribute](
        "AD_Column",
        _.attributeId -> "AD_Column_ID",
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
        _.version -> "Version",
        _.entityType -> "EntityType",
        _.attributeName -> "ColumnName",
        _.entityId -> "AD_Table_ID",
        _.referenceId -> "AD_Reference_ID",
        _.referenceValueId -> "AD_Reference_Value_ID",
        _.valRuleId -> "AD_Val_Rule_ID",
        _.fieldLength -> "FieldLength",
        _.defaultValue -> "DefaultValue",
        _.isKey -> "IsKey",
        _.isParent -> "IsParent",
        _.isMandatory -> "IsMandatory",
        _.isUpdateable -> "IsUpdateable",
        _.readOnlyLogic -> "ReadOnlyLogic",
        _.isIdentifier -> "IsIdentifier",
        _.seqNo -> "SeqNo",
        _.isTranslated -> "IsTranslated",
        _.isEncrypted -> "IsEncrypted",
        _.callout -> "Callout",
        _.vFormat -> "VFormat",
        _.valueMin -> "ValueMin",
        _.valueMax -> "ValueMax",
        _.isSelectionAttribute -> "IsSelectionColumn",
        _.elementId -> "AD_Element_ID",
        _.processId -> "AD_Process_ID",
        _.isSyncDatabase -> "IsSyncDatabase",
        _.isAlwaysUpdateable -> "IsAlwaysUpdateable",
        _.attributeSql -> "ColumnSQL",
        _.mandatoryLogic -> "MandatoryLogic",
        _.infoFactoryClass -> "InfoFactoryClass",
        _.isAutoComplete -> "IsAutoComplete",
        _.isAllowLogging -> "IsAllowLogging",
        _.formatPattern -> "FormatPattern",
        _.chartId -> "AD_Chart_ID",
        _.isRange -> "IsRange",
        _.uuid -> "UUID"
      )

    def attributeProperties: Seq[(String, String)] =
      attributeSchemaMeta.entity.ast.quat.renames.toSeq
  }
}
