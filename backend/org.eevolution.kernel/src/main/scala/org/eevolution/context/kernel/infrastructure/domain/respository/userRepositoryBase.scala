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
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  **/

package org.eevolution.context.kernel.infrastructure.domain.respository

import io.getquill.NamingStrategy
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import org.eevolution.context.kernel.domain.userRepositoryApi
import org.eevolution.context.kernel.domain.userRepositoryApi.UserRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{
  Id,
  TableDirect,
  User
}
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try

trait userRepositoryBase {
  class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
      val context: JdbcContext[Dialect, Naming],
      val blocking: Blocking.Service
  ) extends userRepositoryApi.UserRepository.Service {

    import context._

    override def getById(userId: Id): RIO[UserRepository, Option[User]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[User]
                .filter(_.userId == lift(userId))
            ).map(_.headOption)
          )
        )
      )

    override def getUser(
        partnerId: Id,
        userName: String,
        accountEmail: String
    ): RIO[UserRepository, Option[User]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[User]
                .filter(user =>
                  user.partnerId == lift(partnerId) &&
                    user.name == lift(userName) &&
                    user.email == lift(accountEmail)
                )
            ).map(_.headOption)
          )
        )
      )

    override def getAll: RIO[UserRepository, List[User]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[User])
          )
        )
      )

    override def getAllByClientId(
        tenantId: TableDirect
    ): RIO[UserRepository, List[User]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[User].filter(_.tenantId == lift(tenantId)))
          )
        )
      )

    override def save(user: User): RIO[Any, User] =
      RIO.fromTry(
        Try(EntityWrapper.save(user, userProperties))
      )

    implicit val userSchemaMeta: context.SchemaMeta[User] =
      schemaMeta[User](
        "AD_User",
        _.userId -> "AD_User_ID",
        _.tenantId -> "AD_Client_ID",
        _.organizationId -> "AD_Org_ID",
        _.isActive -> "IsActive",
        _.created -> "Created",
        _.createdBy -> "CreatedBy",
        _.updated -> "Updated",
        _.updatedBy -> "UpdatedBy",
        _.name -> "Name",
        _.email -> "Email",
        _.password -> "Password",
        _.salt -> "Salt",
        _.description -> "Description",
        _.partnerId -> "C_BPartner_ID",
        _.uuid -> "UUID"
      )

    def userProperties: Seq[(String, String)] =
      userSchemaMeta.entity.ast.quat.renames.toSeq
  }
}
