package com.esoapps.scrapping.example

import android.os.Parcelable
import java.io.Serializable

class SelectedCard(var imgUrl:String,
                   var title:String,
                   var desc:String,
                   var articleRef:String,
) : Serializable
