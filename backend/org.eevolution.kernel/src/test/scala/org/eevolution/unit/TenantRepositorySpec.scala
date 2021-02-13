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

package org.eevolution.unit

import org.compiere.Adempiere
import org.compiere.util.{CLogMgt, Env, Ini, Login}
import org.eevolution.context.kernel.domain.tenantRepositoryApi.TenantRepository
import org.eevolution.context.kernel.domain.tenantServiceApi.TenantService
import org.eevolution.context.kernel.domain.tenantServiceModule.TenantServiceLayer
import org.eevolution.context.kernel.domain.{
  tenantRepositoryApi,
  tenantServiceApi
}
import org.eevolution.context.kernel.infrastructure.databaseConfigProviderApi.DatabaseConfigProvider
import org.eevolution.context.kernel.infrastructure.databaseConfigProviderModule.DatabaseConfigProviderLayer
import org.eevolution.context.kernel.infrastructure.databaseProviderApi.DatabaseProvider
import org.eevolution.context.kernel.infrastructure.databaseProviderModule.DatabaseProviderLayer
import org.eevolution.context.kernel.infrastructure.domain.tenantRepositoryModule.TenantRepositoryLayer
import zio.blocking.Blocking
import zio.console.{putStr, putStrLn}
import zio.test.Assertion.equalTo
import zio.test._
import zio.test.environment.TestEnvironment
import zio.{ULayer, ZIO, ZLayer, ZEnv}

import java.util.logging.Level

/**
  * Integration Test for the TenantRepository and TenantService example
  */
object TenantRepositorySpec extends DefaultRunnableSpec {
  val configLayer: ULayer[DatabaseConfigProvider] =
    DatabaseConfigProviderLayer.live
  val databaseLayer: ZLayer[Any, Throwable, DatabaseProvider] =

    configLayer >>> DatabaseProviderLayer.live
  val repositoryLayer: ZLayer[Any, Throwable, TenantRepository] =
    (databaseLayer ++ Blocking.live) >>> TenantRepositoryLayer.live
  val serviceLayer
      : ZLayer[Any, Throwable, TenantRepository with TenantService] =
    repositoryLayer >+> TenantServiceLayer.live

  def spec: Spec[TestEnvironment, TestFailure[Throwable], TestSuccess] =
    suite("Tenant Repository Test")(
      testM("Testing Tenant Repository") {
        startup()
        for {
          tenantWithRepository <-
            tenantRepositoryApi
              .getById(11)
              .provideSomeLayer(repositoryLayer)
          _ <- ZIO.foreach_(tenantWithRepository)(tenant =>
            putStrLn(s"${tenant.name} ${tenant.uuid} ")
          )
          tenantWithService <-
            tenantServiceApi
              .getById(11)
              .provideSomeLayer(serviceLayer)
          _ <- ZIO.foreach_(tenantWithService)(tenant =>
            putStrLn(s"${tenant.name} ${tenant.uuid}")
          )
        } yield assert(tenantWithRepository.get.uuid)(
          equalTo(tenantWithService.get.uuid)
        )
      }.provideSomeLayer(ZEnv.live)
    )

  def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.OFF)
    CLogMgt.setLoggerLevel(Level.OFF, null)

    //Ini.setProperty(Ini.P_PWD,"System")
    //Ini.setProperty(Ini.P_CLIENT, "System")
    //Ini.setProperty(Ini.P_ORG,"*");
    //Ini.setProperty(Ini.P_WAREHOUSE," ")
    //Ini.setProperty(Ini.P_ORG, "HQ")
    //Ini.setProperty(Ini.P_WAREHOUSE, "0")

    Ini.setProperty(Ini.P_UID, "SuperUser")
    Ini.setProperty(Ini.P_PWD, "System")
    Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin")
    Ini.setProperty(Ini.P_CLIENT, "GardenWorld")
    Ini.setProperty(Ini.P_ORG, "HQ");
    Ini.setProperty(Ini.P_WAREHOUSE, "HQ Warehouse")
    Ini.setProperty(Ini.P_LANGUAGE, "English")
    val login = new Login(Env.getCtx())
    if (!login.batchLogin(null))
      System.exit(1)
    CLogMgt.setLoggerLevel(Level.SEVERE, null)
    CLogMgt.setLevel(Level.SEVERE)
  }

}
