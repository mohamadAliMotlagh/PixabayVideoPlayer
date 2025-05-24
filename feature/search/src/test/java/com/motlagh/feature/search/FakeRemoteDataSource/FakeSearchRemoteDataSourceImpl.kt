package com.motlagh.feature.search.FakeRemoteDataSource

import com.motlagh.core.utils.result.resultOf
import com.motlagh.feature.search.data.remote.SearchVideoRemoteDataSource
import com.motlagh.feature.search.data.remote.dto.SearchDTO
import kotlinx.serialization.json.Json


class FakeSearchRemoteDataSourceImpl : SearchVideoRemoteDataSource {
    override fun searchVideos(query: String): Result<SearchDTO> {
        return resultOf { Json.decodeFromString<SearchDTO>(fakeJson) }
    }
}


val fakeJson = """
    {
      "total": 8303,
      "totalHits": 500,
      "hits": [
        {
          "id": 22634,
          "pageURL": "https://pixabay.com/videos/id-22634/",
          "type": "film",
          "tags": "buttercups, buttercup, blossom, bloom, time lapse, yellow, petals, flower, flora, spring flower",
          "duration": 26,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_large.mp4",
              "width": 3840,
              "height": 2120,
              "size": 48810391,
              "thumbnail": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_medium.mp4",
              "width": 2608,
              "height": 1439,
              "size": 21356085,
              "thumbnail": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_small.mp4",
              "width": 1956,
              "height": 1079,
              "size": 12768688,
              "thumbnail": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_tiny.mp4",
              "width": 1304,
              "height": 719,
              "size": 6621423,
              "thumbnail": "https://cdn.pixabay.com/video/2019/04/06/22634-328940142_tiny.jpg"
            }
          },
          "views": 207745,
          "downloads": 112070,
          "likes": 1364,
          "comments": 337,
          "user_id": 4994132,
          "user": "adege",
          "userImageURL": "https://cdn.pixabay.com/user/2022/04/26/15-17-47-992_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 2719,
          "pageURL": "https://pixabay.com/videos/id-2719/",
          "type": "film",
          "tags": "dandelion, flower, yellow, plant, blossom, bloom, pointed flower, nature, meadow, bloom",
          "duration": 23,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_large.mp4",
              "width": 1920,
              "height": 1080,
              "size": 13392449,
              "thumbnail": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_medium.mp4",
              "width": 1280,
              "height": 720,
              "size": 7770061,
              "thumbnail": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_small.mp4",
              "width": 960,
              "height": 540,
              "size": 4940642,
              "thumbnail": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_tiny.mp4",
              "width": 640,
              "height": 360,
              "size": 1769296,
              "thumbnail": "https://cdn.pixabay.com/video/2016/04/05/2719-161687568_tiny.jpg"
            }
          },
          "views": 121203,
          "downloads": 56982,
          "likes": 542,
          "comments": 159,
          "user_id": 1981326,
          "user": "FMFA",
          "userImageURL": "https://cdn.pixabay.com/user/2016/05/06/18-47-35-699_250x250.png",
          "noAiTraining": false
        },
        {
          "id": 161178,
          "pageURL": "https://pixabay.com/videos/id-161178/",
          "type": "film",
          "tags": "flower, gemswurz, yellow, balkan gemsroot, caucasus gemwort, blossom, bloom, bloom, nature, flora, garden, slow motion",
          "duration": 40,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_large.mp4",
              "width": 1920,
              "height": 1080,
              "size": 20000919,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_medium.mp4",
              "width": 1280,
              "height": 720,
              "size": 11476924,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_small.mp4",
              "width": 960,
              "height": 540,
              "size": 8265669,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_tiny.mp4",
              "width": 640,
              "height": 360,
              "size": 4922779,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/01/161178-822846940_tiny.jpg"
            }
          },
          "views": 115614,
          "downloads": 57220,
          "likes": 1000,
          "comments": 9,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 90090,
          "pageURL": "https://pixabay.com/videos/id-90090/",
          "type": "film",
          "tags": "flowers, yellow cosmos, wind, yellow flower, autumn",
          "duration": 42,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 78532870,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 47118969,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 27769696,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 13554166,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90090-620258401_tiny.jpg"
            }
          },
          "views": 13927,
          "downloads": 7988,
          "likes": 191,
          "comments": 28,
          "user_id": 7703165,
          "user": "KIMDAEJEUNG",
          "userImageURL": "https://cdn.pixabay.com/user/2024/10/18/23-44-30-71_250x250.jpg",
          "noAiTraining": true
        },
        {
          "id": 191440,
          "pageURL": "https://pixabay.com/videos/id-191440/",
          "type": "film",
          "tags": "flower, silphie, yellow, blossom, bloom, flora, yellow flower, energy crop, nature",
          "duration": 16,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 25587544,
              "thumbnail": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 11564322,
              "thumbnail": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 7549072,
              "thumbnail": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 4100586,
              "thumbnail": "https://cdn.pixabay.com/video/2023/11/30/191440-890098909_tiny.jpg"
            }
          },
          "views": 9853,
          "downloads": 4490,
          "likes": 177,
          "comments": 2,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 164287,
          "pageURL": "https://pixabay.com/videos/id-164287/",
          "type": "film",
          "tags": "flower, yellow, silphie, insect, bee, blossom, bloom, mixed silphie, flora, summer, nature, yellow flower, energy crop, harvest",
          "duration": 14,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 25253776,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 13311330,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 8902591,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 5039688,
              "thumbnail": "https://cdn.pixabay.com/video/2023/05/24/164287-830461052_tiny.jpg"
            }
          },
          "views": 2258,
          "downloads": 1140,
          "likes": 90,
          "comments": 1,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 127946,
          "pageURL": "https://pixabay.com/videos/id-127946/",
          "type": "film",
          "tags": "mixed silphie, flower, yellow, blossom, bloom, bloom, summer, flora, nature, yellow flower, energy crop, crop, bee, insect",
          "duration": 16,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 52830201,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 29035360,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 12332273,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 6329236,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/15/127946-739712364_tiny.jpg"
            }
          },
          "views": 2866,
          "downloads": 1785,
          "likes": 62,
          "comments": 2,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 175885,
          "pageURL": "https://pixabay.com/videos/id-175885/",
          "type": "film",
          "tags": "flower, yellow, bee, wildflower, insect, nectar, nature, slow motion, pointed flower",
          "duration": 37,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_large.mp4",
              "width": 1920,
              "height": 1080,
              "size": 16338367,
              "thumbnail": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_medium.mp4",
              "width": 1280,
              "height": 720,
              "size": 6477544,
              "thumbnail": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_small.mp4",
              "width": 960,
              "height": 540,
              "size": 4269715,
              "thumbnail": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_tiny.mp4",
              "width": 640,
              "height": 360,
              "size": 2504308,
              "thumbnail": "https://cdn.pixabay.com/video/2023/08/13/175885-854230231_tiny.jpg"
            }
          },
          "views": 4626,
          "downloads": 2216,
          "likes": 114,
          "comments": 0,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 213050,
          "pageURL": "https://pixabay.com/videos/id-213050/",
          "type": "film",
          "tags": "rose, flower, yellow, blossom, rose flower, bloom, nature",
          "duration": 18,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2024/05/22/213050_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 17900968,
              "thumbnail": "https://cdn.pixabay.com/video/2024/05/22/213050_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2024/05/22/213050_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 6816895,
              "thumbnail": "https://cdn.pixabay.com/video/2024/05/22/213050_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2024/05/22/213050_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 3757838,
              "thumbnail": "https://cdn.pixabay.com/video/2024/05/22/213050_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2024/05/22/213050_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 1249720,
              "thumbnail": "https://cdn.pixabay.com/video/2024/05/22/213050_tiny.jpg"
            }
          },
          "views": 10637,
          "downloads": 4180,
          "likes": 129,
          "comments": 2,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 90089,
          "pageURL": "https://pixabay.com/videos/id-90089/",
          "type": "film",
          "tags": "flowers, yellow cosmos, wind, yellow flower, autumn",
          "duration": 39,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 51480717,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 21869522,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 13397989,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 6258231,
              "thumbnail": "https://cdn.pixabay.com/video/2021/09/28/90089-620258393_tiny.jpg"
            }
          },
          "views": 3715,
          "downloads": 2245,
          "likes": 75,
          "comments": 6,
          "user_id": 7703165,
          "user": "KIMDAEJEUNG",
          "userImageURL": "https://cdn.pixabay.com/user/2024/10/18/23-44-30-71_250x250.jpg",
          "noAiTraining": true
        },
        {
          "id": 128136,
          "pageURL": "https://pixabay.com/videos/id-128136/",
          "type": "film",
          "tags": "flower, insect, bee, yellow, mixed silphie, blossom, bloom, summer, flora, nature, yellow flower, energy crop, crop, silphie",
          "duration": 32,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 59409182,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 28530273,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 18715594,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 10315544,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128136-740854602_tiny.jpg"
            }
          },
          "views": 2151,
          "downloads": 1374,
          "likes": 54,
          "comments": 3,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 14521,
          "pageURL": "https://pixabay.com/videos/id-14521/",
          "type": "film",
          "tags": "flower, nature, yellow, daisy, spring flower, garden",
          "duration": 20,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_large.mp4",
              "width": 1920,
              "height": 1080,
              "size": 13059326,
              "thumbnail": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_medium.mp4",
              "width": 1920,
              "height": 1080,
              "size": 13074764,
              "thumbnail": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_small.mp4",
              "width": 1280,
              "height": 720,
              "size": 6207558,
              "thumbnail": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_tiny.mp4",
              "width": 960,
              "height": 540,
              "size": 4089996,
              "thumbnail": "https://cdn.pixabay.com/video/2018/02/25/14521-257440813_tiny.jpg"
            }
          },
          "views": 32434,
          "downloads": 20129,
          "likes": 265,
          "comments": 45,
          "user_id": 1,
          "user": "Pixabay",
          "userImageURL": "https://cdn.pixabay.com/user/2024/06/25/05-18-31-673_250x250.png",
          "noAiTraining": false
        },
        {
          "id": 133337,
          "pageURL": "https://pixabay.com/videos/id-133337/",
          "type": "film",
          "tags": "flower, ant, petals, yellow flower, grass, garden, insect",
          "duration": 22,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 34656539,
              "thumbnail": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 12308436,
              "thumbnail": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 7577398,
              "thumbnail": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 3604022,
              "thumbnail": "https://cdn.pixabay.com/video/2022/10/03/133337-756423957_tiny.jpg"
            }
          },
          "views": 1681,
          "downloads": 836,
          "likes": 19,
          "comments": 2,
          "user_id": 26199248,
          "user": "Pix-Off",
          "userImageURL": "",
          "noAiTraining": false
        },
        {
          "id": 128144,
          "pageURL": "https://pixabay.com/videos/id-128144/",
          "type": "film",
          "tags": "flower, flora, blossom, mixed silphie, yellow, bloom, summer, nature, yellow flower, energy crop, crop, silphie",
          "duration": 20,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 35612176,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 16786564,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 10929864,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 5952344,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128144-740854627_tiny.jpg"
            }
          },
          "views": 12189,
          "downloads": 7401,
          "likes": 156,
          "comments": 6,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 145498,
          "pageURL": "https://pixabay.com/videos/id-145498/",
          "type": "film",
          "tags": "flower, flora, blossom, bloom, mixed silphie, yellow, summer, nature, yellow flower, energy crop, harvest, silphie, slow motion",
          "duration": 50,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_large.mp4",
              "width": 1920,
              "height": 1080,
              "size": 30571068,
              "thumbnail": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_medium.mp4",
              "width": 1280,
              "height": 720,
              "size": 17415085,
              "thumbnail": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_small.mp4",
              "width": 960,
              "height": 540,
              "size": 11718787,
              "thumbnail": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_tiny.mp4",
              "width": 640,
              "height": 360,
              "size": 6151989,
              "thumbnail": "https://cdn.pixabay.com/video/2023/01/06/145498-787039571_tiny.jpg"
            }
          },
          "views": 10770,
          "downloads": 6482,
          "likes": 141,
          "comments": 24,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 222604,
          "pageURL": "https://pixabay.com/videos/id-222604/",
          "type": "film",
          "tags": "flower, rose flower, rose, yellow, blossom, bloom, summer, flora, nature",
          "duration": 12,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2024/07/23/222604_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 11506826,
              "thumbnail": "https://cdn.pixabay.com/video/2024/07/23/222604_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2024/07/23/222604_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 4328651,
              "thumbnail": "https://cdn.pixabay.com/video/2024/07/23/222604_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2024/07/23/222604_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 2288057,
              "thumbnail": "https://cdn.pixabay.com/video/2024/07/23/222604_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2024/07/23/222604_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 690657,
              "thumbnail": "https://cdn.pixabay.com/video/2024/07/23/222604_tiny.jpg"
            }
          },
          "views": 6413,
          "downloads": 2215,
          "likes": 97,
          "comments": 2,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 128141,
          "pageURL": "https://pixabay.com/videos/id-128141/",
          "type": "film",
          "tags": "flower, blossom, yellow, mixed silphie, bloom, summer, flora, nature, yellow flower, energy crop, crop, silphie",
          "duration": 25,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 56778080,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 34201666,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 18670746,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 9638408,
              "thumbnail": "https://cdn.pixabay.com/video/2022/08/17/128141-740854614_tiny.jpg"
            }
          },
          "views": 6461,
          "downloads": 4114,
          "likes": 108,
          "comments": 6,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 15211,
          "pageURL": "https://pixabay.com/videos/id-15211/",
          "type": "film",
          "tags": "crocus, blossom, bloom, bloom, to flourish, yellow, yellow flower, rubber stamp, time lapse, timelapse",
          "duration": 22,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_large.mp4",
              "width": 3840,
              "height": 2160,
              "size": 25393791,
              "thumbnail": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_medium.mp4",
              "width": 2560,
              "height": 1440,
              "size": 8985816,
              "thumbnail": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_small.mp4",
              "width": 1920,
              "height": 1080,
              "size": 5438738,
              "thumbnail": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_tiny.mp4",
              "width": 1280,
              "height": 720,
              "size": 2381708,
              "thumbnail": "https://cdn.pixabay.com/video/2018/03/26/15211-262150448_tiny.jpg"
            }
          },
          "views": 58729,
          "downloads": 35089,
          "likes": 491,
          "comments": 100,
          "user_id": 4994132,
          "user": "adege",
          "userImageURL": "https://cdn.pixabay.com/user/2022/04/26/15-17-47-992_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 37436,
          "pageURL": "https://pixabay.com/videos/id-37436/",
          "type": "film",
          "tags": "sun, sunflower, flower, yellow, nature",
          "duration": 16,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_large.mp4",
              "width": 1920,
              "height": 1080,
              "size": 4587992,
              "thumbnail": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_medium.mp4",
              "width": 1280,
              "height": 720,
              "size": 2621717,
              "thumbnail": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_small.mp4",
              "width": 960,
              "height": 540,
              "size": 1828491,
              "thumbnail": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_tiny.mp4",
              "width": 640,
              "height": 360,
              "size": 831645,
              "thumbnail": "https://cdn.pixabay.com/video/2020/04/28/37436-414024611_tiny.jpg"
            }
          },
          "views": 3365,
          "downloads": 1520,
          "likes": 22,
          "comments": 0,
          "user_id": 7046690,
          "user": "moshehar",
          "userImageURL": "https://cdn.pixabay.com/user/2021/12/13/12-29-54-172_250x250.jpg",
          "noAiTraining": false
        },
        {
          "id": 205553,
          "pageURL": "https://pixabay.com/videos/id-205553/",
          "type": "film",
          "tags": "forsythia, blossom, bloom, yellow, shrub, bloom, spring, nature",
          "duration": 29,
          "videos": {
            "large": {
              "url": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_large.mp4",
              "width": 1920,
              "height": 1080,
              "size": 10557729,
              "thumbnail": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_large.jpg"
            },
            "medium": {
              "url": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_medium.mp4",
              "width": 1280,
              "height": 720,
              "size": 4894920,
              "thumbnail": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_medium.jpg"
            },
            "small": {
              "url": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_small.mp4",
              "width": 960,
              "height": 540,
              "size": 3361146,
              "thumbnail": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_small.jpg"
            },
            "tiny": {
              "url": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_tiny.mp4",
              "width": 640,
              "height": 360,
              "size": 2158606,
              "thumbnail": "https://cdn.pixabay.com/video/2024/03/25/205553-927347775_tiny.jpg"
            }
          },
          "views": 59831,
          "downloads": 25387,
          "likes": 496,
          "comments": 21,
          "user_id": 10327513,
          "user": "NickyPe",
          "userImageURL": "https://cdn.pixabay.com/user/2024/02/05/16-05-14-742_250x250.jpg",
          "noAiTraining": false
        }
      ]
    }
   
""".trimIndent()