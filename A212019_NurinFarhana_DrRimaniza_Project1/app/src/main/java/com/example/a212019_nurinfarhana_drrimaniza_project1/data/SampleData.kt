package com.example.a212019_nurinfarhana_drrimaniza_project1.data

import com.example.a212019_nurinfarhana_drrimaniza_project1.R

object SampleData {

    val brands = listOf(
        Brand(
            id = 1, name = "Patagonia", rating = "Great",
            description = "Pioneer in eco-fabrics and fair trade certified.",
            ratingExplanation = "This brand is a leader in ethical and sustainable fashion. They demonstrate strong commitments across environment, labor rights, and animal welfare.",
            category = "Outdoor", logoRes = R.drawable.patagonia
        ),
        Brand(
            id = 2, name = "Nike", rating = "Not Good Enough",
            description = "Labor and environmental concerns remain unresolved.",
            ratingExplanation = "Despite large revenue, Nike still has unresolved issues with factory labor conditions and limited transparency in their supply chain.",
            category = "Sportswear", logoRes = R.drawable.nikelogo
        ),
        Brand(
            id = 3, name = "Uniqlo", rating = "It's a Start",
            description = "Improving but still lacks full supply chain transparency.",
            ratingExplanation = "Uniqlo has taken some steps toward sustainability but has not yet committed to full disclosure across its manufacturing chain.",
            category = "Clothing", logoRes = R.drawable.uniqlo
        ),
        Brand(
            id = 4, name = "H&M", rating = "Not Good Enough",
            description = "Fast fashion model with limited real sustainability.",
            ratingExplanation = "H&M's Conscious Collection has faced greenwashing allegations. Overproduction remains a core issue with their business model.",
            category = "Clothing", logoRes = R.drawable.hm
        ),
        Brand(
            id = 5, name = "Levi's", rating = "It's a Start",
            description = "Making progress with water-saving denim production.",
            ratingExplanation = "Levi's Water<Less technology has reduced water use significantly, but more progress is needed on labor standards across suppliers.",
            category = "Denim", logoRes = R.drawable.levis
        ),
        Brand(
            id = 6, name = "Adidas", rating = "It's a Start",
            description = "Expanding recycled materials across product lines.",
            ratingExplanation = "Adidas has partnered with Parley for the Oceans on recycled plastics, but overall sustainability targets remain partially unmet.",
            category = "Sportswear", logoRes = R.drawable.adidas
        ),
        Brand(
            id = 7, name = "Zara", rating = "Not Good Enough",
            description = "Overproduction and greenwashing concerns flagged.",
            ratingExplanation = "Zara's Join Life line is undermined by its core fast-fashion model producing thousands of new styles every week.",
            category = "Clothing", logoRes = R.drawable.zara
        ),
        Brand(
            id = 8, name = "Stella McCartney", rating = "Great",
            description = "Luxury brand fully committed to sustainable materials.",
            ratingExplanation = "Stella McCartney avoids leather and fur, uses organic cotton and recycled materials, and publishes full environmental profit and loss reports.",
            category = "Luxury", logoRes = R.drawable.stella
        )
    )

    val clothingItems = listOf(
        ClothingItem(1,  "Classic White Tee",    "H&M",    "T-Shirt",  49.90, 19.90, R.drawable.whitetshirt,    "Soft cotton basic, excess stock clearance."),
        ClothingItem(2,  "Slim Blue Jeans",       "Zara",   "Jeans",   129.00, 49.00, R.drawable.jeanszara,     "Straight fit denim, end-of-season sale."),
        ClothingItem(3,  "Floral Cardigan",       "Uniqlo", "Cardigan", 89.90, 35.90, R.drawable.floralcardigan,  "Light knit, perfect for layering."),
        ClothingItem(4,  "Striped Linen Top",     "Zara",   "Top",      69.90, 25.00, R.drawable.stripedtopzara,       "Breathable linen blend, limited stock."),
        ClothingItem(5,  "Jogger Pants",          "H&M",    "Pants",    79.90, 29.90, R.drawable.joggerpant,     "Comfortable everyday joggers."),
        ClothingItem(6,  "Oversized Hoodie",      "Uniqlo", "Hoodie",   99.00, 45.00, R.drawable.hoodieuniqlo,    "Fleece lining, unisex fit."),
        ClothingItem(7,  "Denim Jacket",          "Levi's", "Jacket",  199.00, 89.00, R.drawable.denimjacket,    "Classic trucker jacket, limited sizes."),
        ClothingItem(8,  "Maxi Floral Dress",     "Zara",   "Dress",   149.00, 59.00, R.drawable.floraldress,     "Flowy summer dress, clearance stock."),
    )

    val news = listOf(
        NewsItem(1, "The Guide to Eco-Friendly Fabrics", "2 days ago", R.drawable.tshirt),
        NewsItem(2, "How Ethical is Nike?",              "5 days ago", R.drawable.nike),
        NewsItem(3, "Thrifting vs Fast Fashion",         "1 week ago", R.drawable.tshirt),
    )
}