package com.example.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import de.blox.graphview.*
import de.blox.graphview.tree.BuchheimWalkerAlgorithm
import de.blox.graphview.tree.BuchheimWalkerConfiguration


class GraphActivity : AppCompatActivity() {

    private var currentNode: Node? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val graph = Graph()
        val node1 = Node("Praveen Narra" + "\nCEO")
        val node2 = Node("Parasuram" + "\nMD")
        val node3 = Node("Muni Hemadri Babu Jogi" + "\nDirector of Technology")
        val node4 = Node("Srinivasulu" + "\nAccount Manager")
        val node5 = Node("Siddharth Takkila" + "\nAsst. Director, Web Development")
        val node6 = Node("Dheeraj Deevi" + "\nManager, IOS")
        val node7 = Node("Hari Krishna Punamalli" + "\nTeam Leader, PHP")
        val node8 = Node("Sai Kumar Vijaygopal" + "\nSr. .Net Developer")
        val node9 = Node("Rohini Prasad Pulipati" + "\nSr. Manager, Web Application")
        val node10 = Node("Tasnim Banu Shaik" + "\nTeam Leader, PHP")
        val node11 = Node("Gouse Mouli Marripadu" + "\nMobile Developer, Android")
        val node12 = Node("Kishore Anandh Reddy" + "\nMobile Developer, Android")
        val node13 = Node("Vignesh Venkatesh" + "\nMobile Developer(TL), Android")
        val node14 = Node("Sailekya Ketheri" + "\nIOS Developer")
        val node15 = Node("Lahari" + "\nIOS Developer")
        val node16 = Node("Banu HarshaVardhan" + "\nnMobile Developer, IOS")
        val node17 = Node("Sindhura PochiReddy VijayBaskar" + "\nMobile Developer, IOS")
        val node18 = Node("TharunKumar Reddy Mannae" + "\nAndroid Developer")
        val node19 = Node("TharunKumar Chittimi" + "\nAndroid Developer")
        val node20 = Node("Ramya Emuri" + "\nAndroid Developer")
        val node21 = Node("Usha Rani Ramesh" + "\nMobile App Developer, Level-2")
        val node22 = Node("Lavanya Dadireddy" + "\nWEB Developer")
        val node23 = Node("Keerthi Putta" + "\nWEB Developer")
        val node24 = Node("Kalpana Nanimela" + "\nWeb Developer, PHP")
        val node25 = Node("Keerthi BellamKonda" + "\nWeb Developer, PHP")
        val node26 = Node("HemaMalini Kondaveeti " + "\nHR Executive")
        val node27 = Node("Vaishnauv Pradeep Sreerama" + "\nHR Executive")
        val node28 = Node("Android Team" + "")
        val node29 = Node("IOS Team" + "")
        val node30 = Node("Testing Team" + "")
        val node31 = Node("Yeswanth Chowdary Emuri" + "\nAssociate Test Engineer(TL)")
        val node32 = Node("Rajeswari Nallavala" + "\nAssociate Test Engineer")
        val node33 = Node("Jahnavi Chigurupathi" + "\nAssociate Test Engineer")
        val node34 = Node("Yogesh Naidu Pollavaram" + "\nAssociate Test Engineer")

        /** ceo*/
        graph.addEdge(node1, node2)
        /**MD*/
        graph.addEdge(node2, node3)
        graph.addEdge(node2, node4)
        graph.addEdge(node2, node5)
        graph.addEdge(node2, node9)
        graph.addEdge(node2, node26)
        graph.addEdge(node2, node27)
        /** Hemadri team*/
        graph.addEdge(node3, node28)
        graph.addEdge(node3, node29)
        graph.addEdge(node3, node30)

        graph.addEdge(node28, node11)
        graph.addEdge(node28, node12)
        graph.addEdge(node28, node13)
        graph.addEdge(node28, node21)
        graph.addEdge(node29, node6)
        /** Siddharth team*/
        graph.addEdge(node5, node7)
        graph.addEdge(node5, node8)
        graph.addEdge(node5, node24)
        graph.addEdge(node5, node25)
        /** Dheeraj team*/
        graph.addEdge(node6, node14)
        graph.addEdge(node6, node15)
        graph.addEdge(node6, node16)
        graph.addEdge(node6, node17)
        /** Rohith team*/
        graph.addEdge(node9, node10)
        graph.addEdge(node9, node22)
        graph.addEdge(node9, node23)
        /** Vignesh team*/
        graph.addEdge(node13, node18)
        graph.addEdge(node13, node19)
        graph.addEdge(node13, node20)
        /** Yeswanth team*/
        graph.addEdge(node30, node31)
        graph.addEdge(node31, node32)
        graph.addEdge(node31, node33)
        graph.addEdge(node31, node34)

        setUpAdapter(graph)

    }

    private fun setUpAdapter(graph: Graph) {

        val graphView = findViewById<GraphView>(R.id.graphView)
        val adapterBaseGraph: BaseGraphAdapter<ViewHolder> =
            object : BaseGraphAdapter<ViewHolder>(graph) {

                override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
                    val view =
                        LayoutInflater.from(parent?.context).inflate(R.layout.node, parent, false)
                    return SimpleViewHolder(view)
                }

                override fun onBindViewHolder(viewHolder: ViewHolder?, data: Any?, position: Int) {
                    (viewHolder as SimpleViewHolder).empName.text = data.toString()
                }

                inner class SimpleViewHolder(itemView: View) : ViewHolder(itemView) {
                    var empName: TextView = itemView.findViewById(R.id.name)
                }
            }

        val configuration = BuchheimWalkerConfiguration.Builder()
            .setSiblingSeparation(100)
            .setLevelSeparation(300)
            .setSubtreeSeparation(300)
            .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
            .build()

        adapterBaseGraph.algorithm = BuchheimWalkerAlgorithm(configuration)
        graphView.adapter = adapterBaseGraph
        graphView.setOnItemClickListener { parent, view, position, id ->
            currentNode = adapterBaseGraph.getNode(position)
            Snackbar.make(
                graphView,
                "Clicked on " + currentNode!!.data.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}