#!/bin/bash
set -x
export FLINK_SOURCE_DOWNLOAD_PATH=$1
export FLINK_RELEASE=$2
export SCALA_VERSION=$3
echo "Building Apache Flink Connector for Kinesis from Flink source code."
echo "Flink source code downloaded location ->" $FLINK_SOURCE_DOWNLOAD_PATH
echo "Flink version to be used 				->" $FLINK_RELEASE
echo "Scala version to be used 				->" $SCALA_VERSION
cd $FLINK_SOURCE_DOWNLOAD_PATH
wget -qO- https://github.com/apache/flink/archive/release-$FLINK_RELEASE.zip | bsdtar -xf-
cd flink-release-$FLINK_RELEASE
mvn clean package -B -DskipTests -Dfast -Pinclude-kinesis -pl flink-connectors/flink-connector-kinesis
mvn install:install-file -Dfile=$FLINK_SOURCE_DOWNLOAD_PATH/flink-release-$FLINK_RELEASE/flink-connectors/flink-connector-kinesis/target/flink-connector-kinesis_$SCALA_VERSION-$FLINK_RELEASE.jar \
-DgroupId=org.apache.flink \
-DartifactId=flink-connector-kinesis_$SCALA_VERSION \
-Dversion=$FLINK_RELEASE -Dpackaging=jar