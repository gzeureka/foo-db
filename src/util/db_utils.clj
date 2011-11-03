(ns util.db-utils
  (:import (org.apache.commons.pool.impl GenericObjectPool)
           (org.apache.commons.dbcp PoolingDataSource PoolableConnectionFactory DriverManagerConnectionFactory)))

(defn setup-data-source [uri]
  (let [connection-factory (DriverManagerConnectionFactory. uri nil)
        connection-pool (GenericObjectPool. nil)
        poolable-connection-factory (PoolableConnectionFactory. connection-factory connection-pool nil nil false true)]
    (PoolingDataSource. connection-pool)))
