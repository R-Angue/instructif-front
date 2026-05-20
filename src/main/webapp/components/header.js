class Header extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
    }

    connectedCallback() {
        this.shadowRoot.innerHTML = `
      <style>
        header {
          background-color: #0a0a23;
          font-family: sans-serif;
        }

        nav ul {
          display: flex;
          align-items: center;
          list-style: none;
          margin: 0;
          padding: 0 20px;
          height: 60px;
        }

        nav ul li:first-child {
          margin-right: auto;
        }

        /* Ces styles pour 'a' ne s'appliqueront plus aux liens de ton <main> */
        a {
          font-weight: 700;
          margin: 0 15px;
          color: #fff;
          text-decoration: none;
        }

        a:hover {
          color: #d0d0d0;
          text-decoration: none;
        }

        .btn-signup {
          border: 1px solid #fff;
          padding: 8px 15px;
          border-radius: 5px;
        }
      </style>

      <header>
        <nav>
          <ul id="nav-list">
            <li><a href="index.html">Accueil</a></li>
            <!-- Les liens seront injectés ici par JS -->
          </ul>
        </nav>
      </header>
        
      <script>
        // Cette partie s'exécute dans le shadow DOM, mais de façon générale il vaut mieux écrire la logique JS dans la classe.
      </script>
    `;

        this.checkConnexion();
    }

    async checkConnexion() {
        try {
            const res = await fetch('http://localhost:8080/mavenproject/ActionServlet?todo=check_connexion').then(r => r.json());
            const navList = this.shadowRoot.getElementById('nav-list');
            
            if (res.succes) {
                if (res.type === "eleve") {
                    navList.insertAdjacentHTML('beforeend', '<li><a href="profil_eleve.html">Profil élève</a></li>');
                } else if (res.type === "intervenant") {
                    navList.insertAdjacentHTML('beforeend', '<li><a href="profil_intervenant.html">Profil intervenant</a></li>');
                }
            } else {
                navList.insertAdjacentHTML('beforeend', '<li><a href="connexion.html">Connexion</a></li>');
                navList.insertAdjacentHTML('beforeend', '<li><a href="inscription.html" class="btn-signup">Créer un compte</a></li>');
            }
        } catch (e) {
            console.error("Erreur lors de la vérification de la connexion:", e);
        }
    }
}

customElements.define('header-component', Header);