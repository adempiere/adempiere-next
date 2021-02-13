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
import org.eevolution.context.dictionary.domain.elementRepositoryApi
import org.eevolution.context.dictionary.domain.elementRepositoryApi.ElementRepository
import org.eevolution.context.dictionary.domain.model.Element
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{Id, TableDirect}
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try

trait elementRepositoryBase {
  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
      val context: JdbcContext[Dialect, Naming],
      val blocking: Blocking.Service
  ) extends elementRepositoryApi.ElementRepository.Service {

    import context._

    override def getById(
        elementId: Id
    ): RIO[ElementRepository, Option[Element]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[Element]
                .filter(_.elementId == lift(elementId))
            ).map(_.headOption)
          )
        )
      )

    override def getAll: RIO[ElementRepository, List[Element]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Element])
          )
        )
      )

    override def getAllByClientId(
        tenantId: TableDirect
    ): RIO[ElementRepository, List[Element]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Element].filter(_.tenantId == lift(tenantId)))
          )
        )
      )

    override def save(element: Element): RIO[Any, Element] =
      RIO.fromTry(
        Try(EntityWrapper.save(element, elementProperties))
      )

    implicit val elementSchemaMeta: context.SchemaMeta[Element] =
      schemaMeta[Element](
        "AD_Element",
        _.elementId -> "AD_Element_ID",
        _.tenantId -> "AD_Client_ID",
        _.organizationId -> "AD_Org_ID",
        _.isActive -> "IsActive",
        _.created -> "Created",
        _.createdBy -> "CreatedBy",
        _.updated -> "Updated",
        _.updatedBy -> "UpdatedBy",
        _.columnName -> "ColumnName",
        _.name -> "Name",
        _.description -> "Description",
        _.help -> "Help",
        _.printName -> "PrintName",
        _.namePO -> "PO_Name",
        _.descriptionPO -> "PO_Description",
        _.helpPO -> "PO_Help",
        _.printNamePO -> "PO_PrintName",
        _.referenceId -> "AD_Reference_ID",
        _.referenceValueId -> "AD_Reference_Value_ID",
        _.fieldLength -> "FieldLength",
        _.entityType -> "EntityType",
        _.uuid -> "UUID"
      )

    def elementProperties: Seq[(String, String)] =
      elementSchemaMeta.entity.ast.quat.renames.toSeq
  }
}
