export class Adresse {
  constructor(
    private _numero?: number | undefined,
    private _rue?: string | undefined,
    private _codePostal?: string | undefined,
    private _ville?: string | undefined
  ) {}

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
