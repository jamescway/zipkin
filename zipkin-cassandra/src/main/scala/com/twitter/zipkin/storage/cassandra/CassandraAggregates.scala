package com.twitter.zipkin.storage.cassandra

import com.twitter.util._
import com.twitter.zipkin.common.Dependencies
import com.twitter.zipkin.storage.Aggregates
import org.twitter.zipkin.storage.cassandra.Repository
import scala.collection.JavaConversions
import scala.collection.JavaConverters._

class CassandraAggregates(repository: Repository) extends Aggregates {
  private[this] val pool = FuturePool.unboundedPool

  def close() = repository.close()

  override def getDependencies(
    startDate: Option[Time],
    endDate: Option[Time]): Future[Dependencies] = {
    return Future.value(Dependencies.monoid.zero)
    // TODO: repository.getDependencies()
  }

  override def storeDependencies(dependencies: Dependencies): Future[Unit] = pool {
    Unit
    //TODO: repository.storeDependencies()
  }

  override def getTopKeyValueAnnotations(serviceName: String): Future[Seq[String]] = pool {
    JavaConversions.asScalaBuffer(repository.getTopAnnotations(serviceName))
  }

  override def storeTopKeyValueAnnotations(
    serviceName: String,
    a: Seq[String]): Future[Unit] = pool {
    repository.storeTopAnnotations(serviceName, a.asJava)
  }

  override def storeTopAnnotations(serviceName: String, a: Seq[String]): Future[Unit] = pool {
    repository.storeTopAnnotations(serviceName, a.asJava)
  }

  override def getTopAnnotations(serviceName: String): Future[Seq[String]] = pool {
    JavaConversions.asScalaBuffer(repository.getTopAnnotations(serviceName))
  }
}
