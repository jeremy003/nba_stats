<?php
$my_dbh = new PDO('mysql:host=localhost;dbname=nba_stats', "stats", "nba");

function db_fetch1($query, $params) {
  global $my_dbh;
  $sth = $my_dbh->prepare($query);
  $sth->execute($params);
  return $sth->fetch();
}

function db_exec($query, $params) {
  global $my_dbh;
  $sth = $my_dbh->prepare($query);
  $sth->execute($params);
  return $sth;
}

function db_update($query, $params) {
  global $my_dbh;
  $sth = $my_dbh->prepare($query);
  if ($sth->execute($params)) {
    return array(TRUE, "No error");
  } else {
    $ei = $sth->errorInfo();
    return array(FALSE, $ei[2]);
  }
}

function db_insert($query, $params) {
  return db_update($query, $params);
}

function db_delete($query, $params) {
  return db_update($query, $params);
}

$west_teams = array("UTA","SAS","SAC","POR","PHX","OKC","NOP","MIN","MEM","HOU","GSW","LAL","LAC","DEN","DAL");
$east_teams = array("WAS","TOR","CLE","PHI","ORL","NYK","MIL","MIA","BOS","IND","DET","CHI","CHA","BRK","ATL");
$team_code_map = array(
  "UTA" => "Utah Jazz",
  "SAS" => "San Antonio Spurs",
  "SAC" => "Sacramento Kings",
  "POR" => "Portland Trail Blazers",
  "PHX" => "Pheonix Suns",
  "OKC" => "Oklahoma City Thunder",
  "NOP" => "New Orleans Pelicans",
  "MIN" => "Minnesota Timberwolves",
  "MEM" => "Memphis Grizzlies",
  "HOU" => "Houston Rockets",
  "GSW" => "Golden State Warriors",
  "LAL" => "Los Angeles Lakers",
  "LAC" => "Los Angeles Clippers",
  "DEN" => "Denver Nuggets",
  "DAL" => "Dallas Mavericks",
  "WAS" => "Washington Wizards",
  "TOR" => "Toronto Raptors",
  "CLE" => "Cleveland Cavaliers",
  "PHI" => "Philadelphia 76ers",
  "ORL" => "Orlando Magic",
  "NYK" => "New York Knicks",
  "MIL" => "Milwaukee Bucks",
  "MIA" => "Miami Heat",
  "BOS" => "Boston Celtics",
  "IND" => "Indiana Pacers",
  "DET" => "Detroit Pistons",
  "CHI" => "Chicago Bulls",
  "CHA" => "Charlotte Hornets",
  "BRK" => "Brooklyn Nets",
  "ATL" => "Atlanta Hawks",
);

?>
