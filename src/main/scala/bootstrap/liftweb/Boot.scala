

package bootstrap.liftweb

import net.liftweb._
import http._
import mapper.{DefaultConnectionIdentifier, DB}
import mongodb.{MongoHost, MongoAddress, DefaultMongoIdentifier, MongoDB}
import sitemap.{SiteMap, Menu, Loc}
import util.{ NamedPF }


class Boot {
  def boot {

  
    // where to search snippet
    LiftRules.addToPackages("no.aslakjo")

    // build sitemap
    val entries = List(Menu("Home") / "index") :::
                  Nil
    
    LiftRules.uriNotFound.prepend(NamedPF("404handler"){
      case (req,failure) => NotFoundAsTemplate(
        ParsePath(List("exceptions","404"),"html",false,false))
    })

    LiftRules.setSiteMap(SiteMap(entries:_*))
    
    // set character encoding
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    //mongodb://<user>:<password>@flame.mongohq.com:27044/hugsar
    MongoDB.defineDbAuth(
      DefaultMongoIdentifier,
      MongoAddress(
        MongoHost("flame.mongohq.com", 27044),
        "hugsar"
      ),
      "hugsar-m",
      "hugsar1"
    )
  }
}