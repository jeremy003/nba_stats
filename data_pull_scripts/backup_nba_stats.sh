#!/bin/bash

current_time=$(date "+%Y.%m.%d")
file_name="nba_stats".$current_time."sql"

#create a database backup
mysqldump -ustats -pnba nba_stats > /home/pi/nba/backups/$file_name

#update database
java -jar PullData.jar
