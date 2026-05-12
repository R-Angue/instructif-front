class Header extends HTMLElement {
    constructor() {
        super();
        // 1. On crée une racine "Shadow" (le mode 'open' permet d'y accéder via JS si besoin)
        this.attachShadow({mode: 'open'});
    }

    connectedCallback() {
        // 2. On injecte le contenu dans le shadowRoot au lieu de innerHTML
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
          <ul>
            <li><a href="index.html">Accueil</a></li>
            <li><a href="connexion.html">Connexion</a></li>
            <li><a href="inscription.html" class="btn-signup">Créer un compte</a></li>
          </ul>
        </nav>
      </header>
    `;
    }
}

customElements.define('header-component', Header);