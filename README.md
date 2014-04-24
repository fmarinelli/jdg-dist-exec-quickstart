Distributed Execution Framework
===============================
Author: Romain Pelisse
Level: Intermediate
Technologies: Infinispan, Dist Exec, Map Reduce
Summary: Demonstrates how to use Infinispan to distribute task over the grid
Target Product: JDG
Product Versions: JDG 6.2
Source: <https://github.com/infinispan/jdg-quickstart>

What is it?
-----------

This quickstart provides a couple of main class to create a full blown grid and test the task distribution features:

* SimpleExecutionNode, a simple node designed to run distributed task dispatch to it
* A DispatcherNode, also a node, but also produce Task load for the grid

There is four different way, not including Map/Reduce to dispatch a task to the grid. For each, you'll find a private method in the DispatcherNode class demonstrating how to implement it:

* Run the Task on a specified node, this requires to give the address of the node, retrieve from the members list. In the example code, we iterate over the address list and give a task to each - effectively doing a "Round Robin" distribution of the task.
* Run on some node, this submit a task to the grid which will assign it to one node for execution.
* Run everywhere, this submit a task that will be executed by all nodes.
* Distribute workload based on keysubset, this is more advance usage, where the task consumes data from cache, where we provided a subset of keys to use. The grid we'll then balance the task to run on the node owning the associated keys.

Note: the previous mechanism are rather crude away to dispatch and distribute task over the grid. A far more effecient and appropriate way to dispatch process over the grid is obviously using the Map/Reduce feature of JDG. If the process to implement can not leverage this feature, then the example above are relevant.

System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss Data Grid 6.2

Install JDG
-----------

1. Obtain JDG library distribution on Red Hat's Customer Portal at https://access.redhat.com/jbossnetwork/restricted/listSoftware.html

Note: the will provided you both a Maven repository, from which Maven will be able to retrieve *compile* needed jars, but also some runtime dependencies (in the lib/ folder) required to run the quickstart.

----------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](../../README.md#build-and-deploy-the-quickstarts) for complete instructions and additional options._

1. Open a command line and navigate to the root directory of this quickstart.
2. Type this command to build and deploy the archive:

        mvn clean package

4. This will create a file at `target/`

5. Run the example application in its directory:

To start the "simple node":

        mvn -Dnode.main.class=org.jboss.as.quickstarts.datagrid.SimpleExecutionNode -Djava.net.preferIPv4Stack=true -Djgroups.bind_addr=127.0.0.1 exec:java

To start the "dispatcher node", which will distribute some work to the grid:

        mvn -Dnode.main.class=org.jboss.as.quickstarts.datagrid.DispatcherNode -Djava.net.preferIPv4Stack=true -Djgroups.bind_addr=127.0.0.1 exec:java

6. To run the quickstart start two (or more) "simple node" and then fire up a "dispatcher node". You'll then see the different way the dispatcher node is distributing task accross the grid.

Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
