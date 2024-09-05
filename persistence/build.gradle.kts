plugins {
    id("checklist.spring-conventions")
    id("org.jooq.jooq-codegen-gradle") version "3.19.11"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    jooqCodegen("com.mysql:mysql-connector-j")
}

jooq {
    configuration {

        // Configure the database connection here
        jdbc {
            driver = "com.mysql.cj.jdbc.Driver"
            url = "jdbc:mysql://localhost:3306/checklist"
            user = "app_user"
            password = "7iMXNMMx6fQeiL"
        }
        generator {
            database {

                // The database dialect from jooq-meta. Available dialects are
                // named org.jooq.meta.[database].[database]Database.
                //
                // Natively supported values are:
                //
                // org.jooq.meta.ase.ASEDatabase
                // org.jooq.meta.auroramysql.AuroraMySQLDatabase
                // org.jooq.meta.aurorapostgres.AuroraPostgresDatabase
                // org.jooq.meta.cockroachdb.CockroachDBDatabase
                // org.jooq.meta.db2.DB2Database
                // org.jooq.meta.derby.DerbyDatabase
                // org.jooq.meta.firebird.FirebirdDatabase
                // org.jooq.meta.h2.H2Database
                // org.jooq.meta.hana.HANADatabase
                // org.jooq.meta.hsqldb.HSQLDBDatabase
                // org.jooq.meta.ignite.IgniteDatabase
                // org.jooq.meta.informix.InformixDatabase
                // org.jooq.meta.ingres.IngresDatabase
                // org.jooq.meta.mariadb.MariaDBDatabase
                // org.jooq.meta.mysql.MySQLDatabase
                // org.jooq.meta.oracle.OracleDatabase
                // org.jooq.meta.postgres.PostgresDatabase
                // org.jooq.meta.redshift.RedshiftDatabase
                // org.jooq.meta.snowflake.SnowflakeDatabase
                // org.jooq.meta.sqldatawarehouse.SQLDataWarehouseDatabase
                // org.jooq.meta.sqlite.SQLiteDatabase
                // org.jooq.meta.sqlserver.SQLServerDatabase
                // org.jooq.meta.sybase.SybaseDatabase
                // org.jooq.meta.teradata.TeradataDatabase
                // org.jooq.meta.trino.TrinoDatabase
                // org.jooq.meta.vertica.VerticaDatabase
                //
                // This value can be used to reverse-engineer generic JDBC DatabaseMetaData (e.g. for MS Access)
                //
                // org.jooq.meta.jdbc.JDBCDatabase
                //
                // This value can be used to reverse-engineer standard jOOQ-meta XML formats
                //
                // org.jooq.meta.xml.XMLDatabase
                //
                // This value can be used to reverse-engineer schemas defined by SQL files
                // (requires jooq-meta-extensions dependency)
                //
                // org.jooq.meta.extensions.ddl.DDLDatabase
                //
                // This value can be used to reverse-engineer schemas defined by JPA annotated entities
                // (requires jooq-meta-extensions-hibernate dependency)
                //
                // org.jooq.meta.extensions.jpa.JPADatabase
                //
                // This value can be used to reverse-engineer schemas defined by Liquibase migration files
                // (requires jooq-meta-extensions-liquibase dependency)
                //
                // org.jooq.meta.extensions.liquibase.LiquibaseDatabase
                //
                // You can also provide your own org.jooq.meta.Database implementation
                // here, if your database is currently not supported
                name = "org.jooq.meta.mysql.MySQLDatabase"

                // All elements that are generated from your schema (A Java regular expression.
                // Use the pipe to separate several expressions) Watch out for
                // case-sensitivity. Depending on your database, this might be
                // important!
                //
                // You can create case-insensitive regular expressions using this syntax: (?i:expr)
                //
                // Whitespace is ignored and comments are possible.
                includes = ".*"

                // All elements that are excluded from your schema (A Java regular expression.
                // Use the pipe to separate several expressions). Excludes match before
                // includes, i.e. excludes have a higher priority
                excludes = """
           UNUSED_TABLE                # This table (unqualified name) should not be generated
         | PREFIX_.*                   # Objects with a given prefix should not be generated
         | SECRET_SCHEMA\.SECRET_TABLE # This table (qualified name) should not be generated
         | SECRET_ROUTINE              # This routine (unqualified name) ...
      """

                // The schema that is used locally as a source for meta information.
                // This could be your development schema or the production schema, etc
                // This cannot be combined with the schemata element.
                //
                // If left empty, jOOQ will generate all available schemata. See the
                // manual's next section to learn how to generate several schemata
                inputSchema = "checklist"
            }

            // Generation flags: See advanced configuration properties
            generate {}
            target {

                // The destination package of your generated classes (within the
                // destination directory)
                //
                // jOOQ may append the schema name to this package if generating multiple schemas,
                // e.g. org.jooq.your.packagename.schema1
                // org.jooq.your.packagename.schema2
                packageName = "com.genenakagaki.checklist.persistence.generated"

                // The destination directory of your generated classes
                directory = rootDir.absolutePath + "/persistence/src/main/java"
           }
        }
    }
}
