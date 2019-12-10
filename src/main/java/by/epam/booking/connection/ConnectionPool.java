package by.epam.booking.connection;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {

    private static ConnectionPool instance;
    private final static int MAX_POOL_SIZE = 10;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static final ReentrantLock getConnectionLock = new ReentrantLock();
    private static final ReentrantLock getInstanceLock = new ReentrantLock();
    private static final ReentrantLock returnConnectionLock = new ReentrantLock();
    private static BlockingQueue<Connection> connections = new LinkedBlockingDeque<>(MAX_POOL_SIZE);

    public static ArrayDeque<Connection> getBusyConnections() {
        return busyConnections;
    }

    public static void setBusyConnections(ArrayDeque<Connection> busyConnections) {
        ConnectionPool.busyConnections = busyConnections;
    }

    private static ArrayDeque<Connection> busyConnections = new ArrayDeque<Connection>(MAX_POOL_SIZE);


    static {
        initConnectionPool();
    }

    private static void initConnectionPool()    {
        while (connections.size() < MAX_POOL_SIZE) {
            connections.offer(DataBaseConnection.createConnection());
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
                getConnectionLock.lock();
                connection = connections.take();
                busyConnections.offer(connection);
            System.out.println( "get Connection " +connections.size());
        } catch (InterruptedException e) {
           //log
        }finally {
            getConnectionLock.unlock();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        try {
            returnConnectionLock.lock();
            busyConnections.remove(connection);
            connections.offer(connection);
            System.out.println("Close Connection " + connections.size());
        } finally {
            returnConnectionLock.unlock();
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            getInstanceLock.lock();
            try {
                if (null == instance) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                getInstanceLock.unlock();
            }
        }
        return instance;
    }


    private  void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
            }
        }
    }

    public void destroyPool() {
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            try {
                connections.take().close();
            } catch (SQLException | InterruptedException e) {
            }
            deregisterDrivers();
        }
    }
}
