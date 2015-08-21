package com.twitter.zipkin.cassandra

import com.datastax.driver.core.Cluster
import com.twitter.zipkin.builder.Builder
import com.twitter.zipkin.storage.Aggregates
import com.twitter.zipkin.storage.cassandra.{CassandraSpanStoreDefaults, CassandraAggregates}
import org.twitter.zipkin.storage.cassandra.Repository

/** Allows [[CassandraAggregates]] to be used with legacy [[Builder]]s. */
case class AggregatesBuilder(
  cluster: Cluster,
  keyspace: String = CassandraSpanStoreDefaults.KeyspaceName
) extends Builder[Aggregates] {self =>

  def apply() = new CassandraAggregates(new Repository(keyspace, cluster))
}
