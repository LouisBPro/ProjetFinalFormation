export class Client {
  constructor(
    private _id?: number | undefined,
    private _prenom?: string | undefined,
    private _nom?: string | undefined,
    private _email?: string | undefined,
    private _login?: string | undefined,
    private _numero?: number | undefined,
    private _rue?: string | undefined,
    private _codePostal?: string | undefined,
    private _ville?: string | undefined
  ) {}

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Getter prenom
   * @return {string}
   */
  public get prenom(): string | undefined {
    return this._prenom;
  }

  /**
   * Getter nom
   * @return {string}
   */
  public get nom(): string | undefined {
    return this._nom;
  }

  /**
   * Getter email
   * @return {string}
   */
  public get email(): string | undefined {
    return this._email;
  }

  /**
   * Getter login
   * @return {string}
   */
  public get login(): string | undefined {
    return this._login;
  }

  /**
   * Getter numero
   * @return {number}
   */
  public get numero(): number | undefined {
    return this._numero;
  }

  /**
   * Getter rue
   * @return {string}
   */
  public get rue(): string | undefined {
    return this._rue;
  }

  /**
   * Getter codePostal
   * @return {string}
   */
  public get codePostal(): string | undefined {
    return this._codePostal;
  }

  /**
   * Getter ville
   * @return {string}
   */
  public get ville(): string | undefined {
    return this._ville;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }

  /**
   * Setter prenom
   * @param {string} value
   */
  public set prenom(value: string | undefined) {
    this._prenom = value;
  }

  /**
   * Setter nom
   * @param {string} value
   */
  public set nom(value: string | undefined) {
    this._nom = value;
  }

  /**
   * Setter email
   * @param {string} value
   */
  public set email(value: string | undefined) {
    this._email = value;
  }

  /**
   * Setter login
   * @param {string} value
   */
  public set login(value: string | undefined) {
    this._login = value;
  }

  /**
   * Setter numero
   * @param {number} value
   */
  public set numero(value: number | undefined) {
    this._numero = value;
  }

  /**
   * Setter rue
   * @param {string} value
   */
  public set rue(value: string | undefined) {
    this._rue = value;
  }

  /**
   * Setter codePostal
   * @param {string} value
   */
  public set codePostal(value: string | undefined) {
    this._codePostal = value;
  }

  /**
   * Setter ville
   * @param {string} value
   */
  public set ville(value: string | undefined) {
    this._ville = value;
  }
}
