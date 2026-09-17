package com.sensitivepro.app.data

object WeaponRepository {

    fun getAllWeapons(): List<Weapon> = buildList {
        addAll(getSMGWeapons())
        addAll(getShotgunWeapons())
        addAll(getRifleWeapons())
        addAll(getMarksmanWeapons())
    }

    fun getByCategory(category: WeaponCategory): List<Weapon> = getAllWeapons().filter { it.category == category }

    fun search(query: String): List<Weapon> {
        if (query.isBlank()) return getAllWeapons()
        return getAllWeapons().filter { it.name.contains(query, ignoreCase = true) }
    }

    private fun getSMGWeapons(): List<Weapon> = listOf(
        Weapon(
            id = "mp40", name = "MP40", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(184, 180, 164, 144, 110, 136, 184),
            descriptionKh = "MP40 ជាស្តេច SMG ក្នុង Free Fire បាញ់លឿនខ្លាំង ដ close range គឺខ្លាំងបំផុត។ ត្រូវការអូសលឿន និងសែនស៊ីធីវខ្ពស់។",
            tipsKh = "• អូសឡើងលើបន្តិចពេលបាញ់ ដើម្បីបាញ់ក្បាល\n• ប្រើ Red Dot កុំប្រើ Scope\n• បាញ់ជិតៗ កុំបាញ់ឆ្ងាយ\n• ទូទៅ 184 អូស 184 ល្អបំផុតសម្រាប់ MP40",
            difficulty = "ងាយស្រួល", fireRate = "លឿនខ្លាំង"
        ),
        Weapon(
            id = "mp5", name = "MP5", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(180, 176, 160, 140, 104, 132, 176),
            descriptionKh = "MP5 មានតុល្យភាពល្អ ជាង MP40 ត្រង់ភាពត្រឹមត្រូវ។ បាញ់ចំគោលដៅបានល្អ ទាំងជិត និងមធ្យម។",
            tipsKh = "• សមស្របសម្រាប់អ្នកចាប់ផ្តើម SMG\n• អូសយឺតជាង MP40 បន្តិច\n• ប្រើ 3-4 គ្រាប់ជាក្រុម",
            difficulty = "ងាយស្រួល", fireRate = "លឿន"
        ),
        Weapon(
            id = "ump", name = "UMP", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(178, 174, 158, 138, 106, 130, 172),
            descriptionKh = "UMP មានកម្លាំងខ្លាំង ជាង MP5 បន្តិច ប៉ុន្តែបាញ់យឺតជាង។ ល្អសម្រាប់បាញ់ចម្ងាយមធ្យម។ សែនស៊ីធីវមធ្យមខ្ពស់។",
            tipsKh = "• UMP បាញ់ចំជាង MP40 ពេលបាញ់ឆ្ងាយ\n• អូសថ្នមៗ មិនបាច់លឿនពេក\n• ដាក់ Grip ដើម្បីកាត់បន្ថយការរំញ័រ",
            difficulty = "មធ្យម", fireRate = "មធ្យម-លឿន"
        ),
        Weapon(
            id = "mac10", name = "MAC10", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(186, 182, 166, 146, 112, 138, 186),
            descriptionKh = "MAC10 បាញ់លឿនបំផុតក្នុង SMG ប៉ុន្តែគ្រាប់រំសេវតិច។ ត្រូវការអូសលឿនខ្លាំងបំផុត។",
            tipsKh = "• កាន់ឲ្យជាប់ អូសលឿនបំផុត\n• បាញ់ក្បាលតែ 3-4 គ្រាប់គឺស្លាប់\n• ប្រើទូទៅ 186 អូស 186",
            difficulty = "ពិបាក", fireRate = "លឿនបំផុត"
        ),
        Weapon(
            id = "p90", name = "P90", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(176, 172, 156, 136, 104, 128, 170),
            descriptionKh = "P90 មានគ្រាប់ 50 គ្រាប់ បាញ់បានយូរ។ ល្អសម្រាប់បាញ់សត្រូវច្រើននាក់។ សែនស៊ីធីវមានតុល្យភាព។",
            tipsKh = "• គ្រាប់ច្រើន បាញ់កុំខ្លាចអស់\n• អូសល្មមៗ មិនលឿនពេក\n• ល្អសម្រាប់ Rush",
            difficulty = "ងាយស្រួល", fireRate = "លឿន"
        ),
        Weapon(
            id = "bizon", name = "Bizon", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(174, 170, 154, 134, 100, 126, 168),
            descriptionKh = "Bizon ក៏មានគ្រាប់ច្រើន (53) បាញ់យឺតជាង P90 បន្តិច ប៉ុន្តែចំគោលដៅជាង។",
            tipsKh = "• សាកសមសម្រាប់អ្នកចូលចិត្តបាញ់យូរ\n• អូសថ្នមៗ",
            difficulty = "ងាយស្រួល", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "thompson", name = "Thompson", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(172, 168, 152, 132, 102, 124, 166),
            descriptionKh = "Thompson បាញ់ធ្ងន់ ចំខ្លាំង ប៉ុន្តែរំញ័រច្រើន។ ត្រូវការ Control ល្អ។ សែនស៊ីធីវទាបជាង SMG ផ្សេងបន្តិច។",
            tipsKh = "• ទប់កាំភ្លើងពេលបាញ់\n• អូសចុះក្រោមបន្តិចដើម្បីទប់រំញ័រ",
            difficulty = "មធ្យម", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "vector", name = "Vector", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(182, 178, 162, 142, 108, 134, 180),
            descriptionKh = "Vector បានបន្ថែមថ្មី បាញ់លឿន និងមាន Akimbo បាន។ សែនស៊ីធីវខ្ពស់ដូច MP40។",
            tipsKh = "• ប្រើ Akimbo បាញ់ 2 ដើម\n• អូសលឿន និងទប់ឲ្យជាប់",
            difficulty = "មធ្យម", fireRate = "លឿនខ្លាំង"
        ),
        Weapon(
            id = "cg15", name = "CG15", category = WeaponCategory.SMG,
            sensitivity = Sensitivity(170, 166, 150, 130, 100, 122, 164),
            descriptionKh = "CG15 ជា SMG ពិសេស បាញ់បានទាំង Close និង Charge បាញ់ឆ្ងាយ។",
            tipsKh = "• Charge ហើយបាញ់ឆ្ងាយបាន\n• សែនស៊ីធីវទាបជាង SMG ធម្មតា",
            difficulty = "មធ្យម", fireRate = "មធ្យម"
        ),
    )

    private fun getShotgunWeapons(): List<Weapon> = listOf(
        Weapon(
            id = "m1887", name = "M1887", category = WeaponCategory.SHOTGUN,
            sensitivity = Sensitivity(156, 150, 130, 120, 96, 116, 160),
            descriptionKh = "M1887 ជាស្តេច Shotgun 2 គ្រាប់គឺស្លាប់។ ត្រូវការសែនស៊ីធីវទាប ដើម្បីអូសឲ្យចំក្បាល។ អូសយឺតៗ តែត្រូវចំ។",
            tipsKh = "• អូសមួយដៃឲ្យចំក្បាល\n• ទូទៅ 156 កុំខ្ពស់ពេក\n• បាញ់ហើយរត់ ចាំបាញ់ម្តងទៀត\n• ត្រូវអូសយឺត តែចំក្បាល",
            difficulty = "ពិបាក", fireRate = "យឺត"
        ),
        Weapon(
            id = "m1014", name = "M1014", category = WeaponCategory.SHOTGUN,
            sensitivity = Sensitivity(164, 160, 140, 124, 100, 120, 168),
            descriptionKh = "M1014 មាន 6 គ្រាប់ បាញ់លឿនជាង M1887។ ល្អសម្រាប់បាញ់ជាប់ៗគ្នា។ សែនស៊ីធីវខ្ពស់ជាង M1887 បន្តិច។",
            tipsKh = "• បាញ់ 2-3 គ្រាប់ជាប់គ្នា\n• អូសលឿនជាង M1887 បន្តិច",
            difficulty = "មធ្យម", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "mag7", name = "Mag-7", category = WeaponCategory.SHOTGUN,
            sensitivity = Sensitivity(168, 164, 144, 128, 104, 124, 172),
            descriptionKh = "Mag-7 មាន 8 គ្រាប់ បាញ់លឿនបំផុតក្នុង Shotgun។ សែនស៊ីធីវខ្ពស់ជាងគេក្នុង Shotgun។",
            tipsKh = "• គ្រាប់ច្រើន បាញ់បានច្រើនដង\n• អូសលឿន តែត្រូវចំ",
            difficulty = "មធ្យម", fireRate = "លឿន"
        ),
        Weapon(
            id = "trogon", name = "Trogon", category = WeaponCategory.SHOTGUN,
            sensitivity = Sensitivity(160, 156, 136, 120, 98, 118, 164),
            descriptionKh = "Trogon ជា Shotgun ថ្មី មាន Grenade Launcher ភ្ជាប់មកជាមួយ។ សែនស៊ីធីវមានតុល្យភាព។",
            tipsKh = "• ប្រើ Grenade បាញ់ឆ្ងាយបាន\n• Shotgun បាញ់ជិត",
            difficulty = "មធ្យម", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "m590", name = "M590", category = WeaponCategory.SHOTGUN,
            sensitivity = Sensitivity(158, 152, 132, 122, 98, 116, 162),
            descriptionKh = "M590 ជា Shotgun 3 គ្រាប់បាញ់ម្តង បាញ់ធ្ងន់ខ្លាំង។ ត្រូវការចំគោលដៅល្អ។",
            tipsKh = "• បាញ់ម្តង 3 គ្រាប់\n• អូសឲ្យចំកណ្តាលខ្លួនសត្រូវ",
            difficulty = "ពិបាក", fireRate = "យឺត"
        ),
        Weapon(
            id = "spas12", name = "SPAS12", category = WeaponCategory.SHOTGUN,
            sensitivity = Sensitivity(162, 158, 138, 124, 100, 120, 166),
            descriptionKh = "SPAS12 បាញ់ 5 គ្រាប់ មានតុល្យភាពរវាង M1887 និង M1014។",
            tipsKh = "• ល្អសម្រាប់អ្នកចាប់ផ្តើម Shotgun",
            difficulty = "ងាយស្រួល", fireRate = "មធ្យម"
        ),
    )

    private fun getRifleWeapons(): List<Weapon> = listOf(
        Weapon(
            id = "ak47", name = "AK47", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(176, 170, 156, 140, 110, 130, 174),
            descriptionKh = "AK47 កម្លាំងខ្លាំងបំផុត ប៉ុន្តែរំញ័រខ្លាំង។ ត្រូវការទប់ និងអូសចុះក្រោម។ សែនស៊ីធីវខ្ពស់ តែត្រូវ Control។",
            tipsKh = "• អូសចុះក្រោមពេលបាញ់ ដើម្បីទប់រំញ័រ\n• បាញ់ 3-4 គ្រាប់ជាក្រុម កុំបាញ់ជាប់\n• ទូទៅ 176 អូស 174",
            difficulty = "ពិបាក", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "m4a1", name = "M4A1", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(172, 168, 152, 136, 106, 126, 168),
            descriptionKh = "M4A1 មានតុល្យភាពល្អបំផុត ងាយស្រួលប្រើ រំញ័រតិច។ ល្អសម្រាប់អ្នកគ្រប់កម្រិត។",
            tipsKh = "• ល្អបំផុតសម្រាប់អ្នកចាប់ផ្តើម Rifle\n• អូសល្មមៗ មិនបាច់ខ្លាំង",
            difficulty = "ងាយស្រួល", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "scar", name = "SCAR", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(174, 170, 154, 138, 108, 128, 170),
            descriptionKh = "SCAR ស្រដៀង M4A1 តែបាញ់ឆ្ងាយចំជាង។ ល្អសម្រាប់បាញ់ចម្ងាយមធ្យម-ឆ្ងាយ។",
            tipsKh = "• ប្រើ Scope 2x-4x ល្អ\n• អូសថ្នមៗ",
            difficulty = "ងាយស្រួល", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "xm8", name = "XM8", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(178, 172, 158, 142, 112, 132, 176),
            descriptionKh = "XM8 មាន Scope 2x ស្រាប់ បាញ់លឿន និងចំ។ ល្អសម្រាប់បាញ់ក្បាល។",
            tipsKh = "• មាន Scope ស្រាប់ មិនបាច់រក\n• អូសលឿនជាង M4A1 បន្តិច",
            difficulty = "ងាយស្រួល", fireRate = "លឿន"
        ),
        Weapon(
            id = "g36", name = "G36", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(170, 166, 150, 134, 104, 124, 166),
            descriptionKh = "G36 មាន 2 Mode បាញ់ធម្មតា និង Scope។ បាញ់ចំ និងរំញ័រតិច។",
            tipsKh = "• ប្រើ Scope Mode ពេលបាញ់ឆ្ងាយ",
            difficulty = "ងាយស្រួល", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "aug", name = "AUG", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(172, 168, 152, 136, 106, 126, 170),
            descriptionKh = "AUG បាញ់ចំខ្លាំង រំញ័រតិច ល្អសម្រាប់បាញ់ចម្ងាយឆ្ងាយ។",
            tipsKh = "• បាញ់ឆ្ងាយបានល្អ\n• អូសថ្នមៗ",
            difficulty = "ងាយស្រួល", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "parafal", name = "Parafal", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(166, 162, 148, 132, 102, 122, 164),
            descriptionKh = "Parafal កម្លាំងខ្លាំងជាង SCAR តែបាញ់យឺតជាង។ បាញ់ម្តងៗ ចំខ្លាំង។",
            tipsKh = "• បាញ់ Single Shot ល្អ\n• កុំបាញ់ Auto ពេលឆ្ងាយ",
            difficulty = "មធ្យម", fireRate = "យឺត"
        ),
        Weapon(
            id = "plasma", name = "Plasma", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(180, 174, 160, 144, 114, 134, 178),
            descriptionKh = "Plasma ជា Rifle ថ្មី បាញ់លឿនខ្លាំង និងមានប្រសិទ្ធភាពខ្ពស់។ សែនស៊ីធីវខ្ពស់។",
            tipsKh = "• បាញ់លឿន ត្រូវអូសលឿន\n• ល្អសម្រាប់ Rush",
            difficulty = "មធ្យម", fireRate = "លឿនខ្លាំង"
        ),
        Weapon(
            id = "groza", name = "Groza", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(176, 172, 156, 140, 110, 130, 174),
            descriptionKh = "Groza មានតែក្នុង Airdrop កម្លាំងខ្លាំង និងបាញ់លឿន។",
            tipsKh = "• រកក្នុង Airdrop\n• បាញ់ខ្លាំងណាស់",
            difficulty = "មធ្យម", fireRate = "លឿន"
        ),
        Weapon(
            id = "famas", name = "FAMAS", category = WeaponCategory.RIFLE,
            sensitivity = Sensitivity(168, 164, 150, 134, 104, 124, 166),
            descriptionKh = "FAMAS បាញ់ 3 គ្រាប់ម្តង បាញ់ចំខ្លាំង។",
            tipsKh = "• បាញ់ Burst 3 គ្រាប់\n• អូសឲ្យចំក្បាល",
            difficulty = "មធ្យម", fireRate = "មធ្យម"
        ),
    )

    private fun getMarksmanWeapons(): List<Weapon> = listOf(
        Weapon(
            id = "ac80", name = "AC80", category = WeaponCategory.MARKSMAN,
            sensitivity = Sensitivity(152, 148, 136, 124, 100, 116, 156),
            descriptionKh = "AC80 បាញ់ 2 គ្រាប់គឺស្លាប់ លឿន និងខ្លាំង។ ល្អបំផុតក្នុង Marksman។ សែនស៊ីធីវទាប ដើម្បីបាញ់ចំ។",
            tipsKh = "• បាញ់ 2 គ្រាប់ ក្បាលស្លាប់\n• អូសយឺតៗ តែចំ\n• ទូទៅ 152 កុំខ្ពស់ពេក",
            difficulty = "មធ្យម", fireRate = "លឿន"
        ),
        Weapon(
            id = "sks", name = "SKS", category = WeaponCategory.MARKSMAN,
            sensitivity = Sensitivity(150, 146, 134, 122, 98, 114, 152),
            descriptionKh = "SKS បាញ់លឿន មាន 10 គ្រាប់។ ល្អសម្រាប់បាញ់ជាប់ៗគ្នា។",
            tipsKh = "• បាញ់លឿន តែត្រូវចំ\n• ល្អសម្រាប់អ្នកចាប់ផ្តើម Marksman",
            difficulty = "ងាយស្រួល", fireRate = "លឿន"
        ),
        Weapon(
            id = "m14", name = "M14", category = WeaponCategory.MARKSMAN,
            sensitivity = Sensitivity(148, 144, 132, 120, 96, 112, 150),
            descriptionKh = "M14 បាញ់ធ្ងន់ កម្លាំងខ្លាំង ប៉ុន្តែបាញ់យឺត។",
            tipsKh = "• បាញ់យឺត តែខ្លាំង\n• ចំ 3 គ្រាប់ស្លាប់",
            difficulty = "មធ្យម", fireRate = "យឺត"
        ),
        Weapon(
            id = "svd", name = "SVD", category = WeaponCategory.MARKSMAN,
            sensitivity = Sensitivity(144, 140, 128, 116, 90, 108, 146),
            descriptionKh = "SVD (Dragunov) មាន Scope 4x ស្រាប់ បាញ់ឆ្ងាយល្អបំផុត។ សែនស៊ីធីវទាបបំផុត ដើម្បីបាញ់ចំ 100%។",
            tipsKh = "• មាន Scope 4x ស្រាប់\n• អូសយឺតបំផុត តែត្រូវចំក្បាល\n• ល្អសម្រាប់ Sniper",
            difficulty = "ពិបាក", fireRate = "យឺត"
        ),
        Weapon(
            id = "woodpecker", name = "Woodpecker", category = WeaponCategory.MARKSMAN,
            sensitivity = Sensitivity(146, 142, 130, 118, 94, 110, 148),
            descriptionKh = "Woodpecker បាញ់ខ្លាំង ជាង SKS ប៉ុន្តែរំញ័រច្រើន។",
            tipsKh = "• បាញ់ខ្លាំង តែរំញ័រ\n• ទប់ឲ្យជាប់",
            difficulty = "ពិបាក", fireRate = "មធ្យម"
        ),
        Weapon(
            id = "winchester", name = "Winchester", category = WeaponCategory.MARKSMAN,
            sensitivity = Sensitivity(154, 150, 138, 126, 102, 118, 158),
            descriptionKh = "Winchester (M1887? ) ជា Marksman បាញ់លឿន មាន 8 គ្រាប់។",
            tipsKh = "• បាញ់លឿន គ្រាប់ច្រើន",
            difficulty = "ងាយស្រួល", fireRate = "លឿន"
        ),
        Weapon(
            id = "m28b", name = "M28B", category = WeaponCategory.MARKSMAN,
            sensitivity = Sensitivity(142, 138, 126, 114, 88, 106, 144),
            descriptionKh = "M28B ជា Sniper ពិត បាញ់ 1 គ្រាប់ស្លាប់ តែបាញ់យឺតបំផុត។",
            tipsKh = "• បាញ់ 1 គ្រាប់ស្លាប់\n• ត្រូវចំក្បាល 100%",
            difficulty = "ពិបាកខ្លាំង", fireRate = "យឺតបំផុត"
        ),
    )
}
