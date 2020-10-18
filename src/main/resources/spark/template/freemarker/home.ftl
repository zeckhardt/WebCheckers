<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->
    <div>
        <#if players??>
            Players online:
            <#list players as player>
                <#if player.name != currentUser.name>
                    </br>
                    <form action="/game" method="GET">
                        ${player.name} <button name="otherPlayer" type="submit" value=${player.name}> Play </button>
                    </form>
                </#if>
            </#list>
        <#else>
            Other players online: ${numPlayers}
        </#if>
    </div>

  </div>

</div>
</body>

</html>
