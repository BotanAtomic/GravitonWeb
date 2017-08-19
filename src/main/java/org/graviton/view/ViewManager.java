package org.graviton.view;

import org.graviton.model.Account;
import org.graviton.model.Player;
import org.graviton.model.Server;
import org.graviton.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewManager {

    @Autowired
    private ServerService serverService;

    public String generateHeader(byte index, boolean connected) {
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"header\">")
                .append("<div class=\"navbar navbar-default\" role=\"navigation\" ng-controller=\"navbarController\">")
                .append("<div class=\"container\">")
                .append("<div class=\"navbar-header\">")
                .append("<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-responsive-collapse\">")
                .append("<span class=\"sr-only\">Barre de navigation</span>")
                .append("<i class=\"fa fa-bars fa-lg\"></i>")
                .append("</button>")
                .append("<a class=\"navbar-brand\" href=\"\">")
                .append("<img id=\"logo-header\" src=\"..\\assets\\img\\logo.png\" alt=\"Logo\">")
                .append("</a></div>")
                .append("<div class=\"collapse navbar-collapse navbar-responsive-collapse\">")
                .append("<ul class=\"nav navbar-nav navbar-right\">");


        builder.append("<li")
                .append(index == 0 ? " class='active'>" : ">")
                .append("<a href=\"..\">Acceuil</a></li>");

        if (connected) {
            builder.append((index == 6 ? "<li class=\"active\">" : "<li class=\"dropdown\">"));
            builder.append("<a class=\"dropdown-toggle\" data-toggle=\"dropdown\">Profil</a>");
            builder.append("<ul class=\"dropdown-menu\">");
            builder.append("<li><a href=\"..\\account\\\"><i class=\"fa fa-user\"></i> Mon compte</a></li>");
            builder.append("<li><a href=\"..\\logout\\\"><i class=\"fa fa-sign-out\"></i> Déconnexion</a></li>");
            builder.append("</ul></li>");
        }

        builder.append("<li")
                .append(index == 1 ? " class='active'>" : ">")
                .append("<a href=\"..\\ladder\\\">Classements</a></li>")

                .append("<li")
                .append(index == 2 ? " class='active'>" : ">")
                .append("<a href=\"..\\join\\\">Nous rejoindre</a></li>")

                .append("<li")
                .append(index == 3 ? " class='active'>" : ">")
                .append("<a href=\"..\\faq\\\">FAQ</a></li>")

                .append("<li")
                .append(index == 4 ? " class='active'>" : ">")
                .append("<a href=\"..\\support\\\">Support</a></li>")

                .append("<li")
                .append(index == 5 ? " class='active'>" : ">")
                .append("<a href=\"vote_link\" target=\"_blank\">Vote</a></li>")

                .append("</ul></div></div></div></div>");

        return builder.toString();
    }

    public String generateBreadCrumbs(String name) {
        return "<div class=\"breadcrumbs margin-bottom-" + (name.equalsIgnoreCase("Acceuil") ? 0 : 40) + "\">" +
                "<div class=\"container\">" +
                "<h1 class=\"pull-left\">" + name + "</h1>" +
                "<ul class=\"pull-right breadcrumb\">" +
                "<li><a href=\"..\">Acceuil</a></li>" +
                "<li class=\"active\">" + name + "</li>" +
                "</ul>" +
                "</div>" +
                "</div>";
    }


    public String generateTop(Account account, Server selectedServer) {
        StringBuilder serverDataBuilder = new StringBuilder();

        serverService.findAll().forEach(server -> {
            boolean active = selectedServer.equals(server);
            serverDataBuilder.append("<li active=\"").append(active).append("\"> ")
                    .append("<a href=\"..\\selectServer?id=").append(server.getId()).append("\">")
                    .append("<i class=\"").append(!active ? "fa" : "fa fa-check").append("\"> ").append(server.getKey())
                    .append("</i></a></li>");
        });

        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"top\">")
                .append("<div class=\"container\">")
                .append("<ul class=\"loginbar pull-right\">")
                .append("<li><i style='color : green;' class=\"fa fa-globe\"></i>")
                .append("<a> Serveurs</a>").append("<ul class=\"language\">").append(serverDataBuilder).append("</ul></li>");


        if (account == null) {
            builder.append("<li class=\"devider\"></li>" +
                    "<li><i class=\"fa fa-sign-in\"></i>" +
                    "<a href=\"..\\login\\\"> Connexion</a>" +
                    "</li><li class=\"devider\"></li>" +
                    "<li><i class=\"fa fa-book\"></i><a href=\"..\\register\\\"> Inscription</a></li></ul></div></div>");
        } else {
            builder.append("<li class=\"devider\"></li>" + "<li>" + "<a>Bienvenue <b><i>")
                    .append(account.getName())
                    .append("</i></b></a>")
                    .append("</li>")
                    .append("<li class=\"devider\"></li>")
                    .append("<li>")
                    .append("<a href=\"..\\logout\\\">Deconnexion ")
                    .append("<i class=\"fa fa-sign-in\"></i></a>")
                    .append("</li></ul></div></div>");
        }

        return builder.toString();
    }



}
