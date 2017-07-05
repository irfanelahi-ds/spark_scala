//removing header and trailer using zipWithIndex:
  def stripLines(headerLines:Int,footerLines:Int,filename:String,delimiter:String)={

      val file_rdd=sc.textFile(filename)
      val file_rdd_length=file_rdd.count
      val file_rddx2=file_rdd.zipWithIndex.map{case(pcontent,pindex)=>(pindex,pcontent)}.filter{case(pindex,pcontent)=>{if (headerLines>0) !((0 to (headerLines-1)).toList.contains(pindex)) else true} && 
{if (footerLines>0) !((file_rdd_length to (file_rdd_length-footerLines) by -1).toList.contains(pindex)) else true}}.map{case(pindex,pcontent)=>pcontent.split(delimiter)}
    file_rddx2
  }
