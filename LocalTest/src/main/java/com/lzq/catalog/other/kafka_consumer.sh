#!/bin/bash

#kafka-topics.sh --bootstrap-server 172.16.101.17:9098 --list

#kafka-console-consumer.sh --bootstrap-server 172.16.100.109:9092 -topic wujuan --from-beginning -max-messages 1
#kafka-console-consumer.sh --bootstrap-server 172.16.100.109:9092 -topic wujuan_source_topic --from-beginning
kafka-console-consumer.sh --bootstrap-server 172.16.100.109:9092 -topic wujuan_city --from-beginning


#kafka-console-consumer.sh --bootstrap-server 172.16.100.109:9092 -topic wujuan_sink_topic --from-beginning


