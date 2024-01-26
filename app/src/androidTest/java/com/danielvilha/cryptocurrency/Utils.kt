import com.danielvilha.models.Coin
import com.danielvilha.models.CoinDetail
import com.danielvilha.models.Links
import com.danielvilha.models.LinksExtended
import com.danielvilha.models.Stats
import com.danielvilha.models.Tag
import com.danielvilha.models.TeamMember
import com.danielvilha.models.Whitepaper

/**
 * Created by Daniel Ferreira de Lima Vilha 21/01/2024.
 */

fun createCoin(id: String, name: String, rank: Int, symbol: String, type: String) =
    Coin(
        id = id,
        isActive = true,
        isNew = true,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type
    )

fun createCoinDetails() =
    CoinDetail(
        description= "Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.",
        developmentStatus = "Working product",
        firstDataAt = "2010-07-17T00:00:00Z",
        hardwareWallet=true,
        hashAlgorithm="SHA256",
        id="btc-bitcoin",
        isActive=true,
        isNew=false,
        lastDataAt="2024-01-21T18:40:00Z",
        links= Links(
            explorer =
                arrayOf("https://blockchair.com/bitcoin/?from=coinpaprika",
                "https://blockchain.com/explorer",
                "https://blockstream.info/",
                "https://live.blockcypher.com/btc/",
                "https://btc.cryptoid.info/btc/").toList(),
            facebook = arrayOf("https://www.facebook.com/bitcoins/").toList(),
            reddit=arrayOf("https://www.reddit.com/r/bitcoin").toList(),
            sourceCode=arrayOf("https://github.com/bitcoin/bitcoin").toList(),
            website=arrayOf("https://bitcoin.org/").toList(),
            youtube=arrayOf("https://www.youtube.com/watch?v=Gc2en3nHxA4&").toList()
        ),
        linksExtended=arrayOf(
            LinksExtended(stats=null, type="blog", url="https://bitcoin.org/en/blog"),
            LinksExtended(stats=null, type="explorer", url="https://blockchair.com/bitcoin/?from=coinpaprika"),
            LinksExtended(stats=null, type="explorer", url="https://blockchain.com/explorer"),
            LinksExtended(stats=null, type="explorer", url="https://blockstream.info/"),
            LinksExtended(stats=null, type="explorer", url="https://live.blockcypher.com/btc/"),
            LinksExtended(stats=null, type="explorer", url="https://btc.cryptoid.info/btc/"),
            LinksExtended(stats=null, type="facebook", url="https://www.facebook.com/bitcoins/"),
            LinksExtended(stats=null, type="message_board", url="https://bitcointalk.org"),
            LinksExtended(
                stats= Stats(contributors=0, followers=0, stars=0, subscribers=5958815),
                type="reddit",
                url="https://www.reddit.com/r/bitcoin"
            ),
            LinksExtended(
                stats=Stats(contributors=1162, followers=0, stars=73452, subscribers=0),
                type="source_code",
                url="https://github.com/bitcoin/bitcoin"
            ),
            LinksExtended(
                stats=Stats(contributors=0, followers=161310, stars=0, subscribers=0),
                type="twitter",
                url="https://twitter.com/bitcoincoreorg"
            ),
            LinksExtended(stats=null, type="wallet", url="https://electrum.org/#download"),
            LinksExtended(stats=null, type="website", url="https://bitcoin.org/"),
            LinksExtended(stats=null, type="youtube", url="https://www.youtube.com/watch?v=Gc2en3nHxA4&")
        ).toList(),
        message="",
        name="Bitcoin",
        openSource=true,
        orgStructure="Decentralized",
        proofType="Proof of Work",
        rank=1,
        startedAt="2009-01-03T00:00:00Z",
        symbol="BTC",
        tags= arrayOf(
            Tag(coinCounter=10, icoCounter=0, id="segwit", name="Segwit"),
            Tag(coinCounter=1071, icoCounter=40, id="cryptocurrency", name="Cryptocurrency"),
            Tag(coinCounter=517, icoCounter=14, id="proof-of-work", name="Proof Of Work"),
            Tag(coinCounter=169, icoCounter=39, id="payments", name="Payments"),
            Tag(coinCounter=47, icoCounter=1, id="sha256", name="Sha256"),
            Tag(coinCounter=272, icoCounter=18, id="mining", name="Mining"),
            Tag(coinCounter=6, icoCounter=0, id="lightning-network", name="Lightning Network")
        ).toList(),
        team= arrayOf(
            TeamMember(id="satoshi-nakamoto", name="Satoshi Nakamoto", position="Founder"),
            TeamMember(id="wladimir-j-van-der-laan", name="Wladimir J. van der Laan", position="Blockchain Developer"),
            TeamMember(id="jonas-schnelli", name="Jonas Schnelli", position="Blockchain Developer"),
            TeamMember(id="marco-falke", name="Marco Falke", position="Blockchain Developer"),
            TeamMember(id="rahul", name="Rahul", position="Node js Developer"),
            TeamMember(id="ashutosh", name="Ashutosh", position="Whale Miner"),
            TeamMember(id="turd-fergason", name="Turd Fergason", position="Blockchain Devolper")
        ).toList(),
        type="coin",
        whitePaper= Whitepaper(
            link="https://static.coinpaprika.com/storage/cdn/whitepapers/215.pdf",
            thumbnail="https://static.coinpaprika.com/storage/cdn/whitepapers/217.jpg"
        )
    )