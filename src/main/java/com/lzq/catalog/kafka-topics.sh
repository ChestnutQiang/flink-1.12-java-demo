#!/bin/bash

kafka-topics.sh --bootstrap-server 172.16.100.109:9092 --topic wujuan_city --delete
kafka-topics.sh --bootstrap-server 172.16.100.109:9092 --topic wujuan_city --create --partitions 1 --replication-factor 1
kafka-topics.sh --bootstrap-server 172.16.100.109:9092 --list | grep wujuan_city